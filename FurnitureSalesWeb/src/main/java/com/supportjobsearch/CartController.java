package com.supportjobsearch;
import com.google.gson.Gson;
import com.supportjobsearch.service.CategoryService;
import com.supportjobsearch.service.ProductService;
import com.supportjobsearch.service.WarehouseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "Cart", value = "/CartController")
public class CartController extends HttpServlet {

//8.3.2. doGet() -Chuyển hướng đến giao diện cart View-
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Lay du lieu category de hien thi len giao dien
        ProductService service = ProductService.getInstance();
        CategoryService cateService = CategoryService.getInstance();

        List<Product> data;
        List<Category> categories;
        try {
            data = service.getAllProducts();
            categories = cateService.getAllCategory();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        int catePerCol = 5;
        HashMap<Integer, List<Category>> mapCate = new HashMap<>();

        int countCol = categories.size() % catePerCol == 0 ? categories.size() / catePerCol : categories.size() / catePerCol + 1;

        for (int i = 0; i < countCol; i++) {
            int index = i * catePerCol;
            for (int j = index; j < index + catePerCol; j++) {
                if (!mapCate.containsKey(i)) {
                    List<Category> list = new ArrayList<>();
                    list.add(categories.get(j));
                    mapCate.put(i, list);
                } else {
                    if (j < categories.size()) mapCate.get(i).add(categories.get(j));
                    else break;
                }
            }
        }

        HttpSession session = req.getSession(true);

        req.setAttribute("mapCate", mapCate);
        req.getRequestDispatcher("/views/web/cart/CartView.jsp").forward(req, resp);
    }

//    8.3.3. doPost() -Lấy dữ liệu của giỏ hàng và hiển thị lên-
//    8.3.7. doPost() -update lại số lượng của sản phẩm
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WarehouseService warehouseService = WarehouseService.getInstance();
        HttpSession session = req.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        //Lấy trên người dùng
        User u = (User) session.getAttribute("auth");
        String uName;
        if (u == null) uName = null;
        else uName = u.getUsername();

        // Nhận dữ liệu từ AJAX
        String json = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        Gson gson = new Gson();
        CartItem newItem = gson.fromJson(json, CartItem.class);

        double total = 0;
        if (u != null) {
            //lấy ra action cần thực hiện
            String action = req.getParameter("action");
            if ("remove".equals(action)) {
                cart.remove(newItem.getId());
            } else if ("plus".equals(action)) {
                // Kiểm tra sản phẩm có tồn tại trong giỏ chưa
                for (CartItem item : cart.getListCartItem()) {
                    if (item.getId() == (newItem.getId())) {
//                       8.3.7. doPost()  -update lại số lượng của sản phẩm
                        cart.update(item.getId(), item.getQuantity() - 1);
                        break;
                    }
                }

            } else {
                // Kiểm tra sản phẩm có tồn tại trong giỏ chưa
                boolean exists = false;
                for (CartItem item : cart.getListCartItem()) {
                    if (item.getId() == (newItem.getId())) {
                        int t = warehouseService.getAmountProductInWarehouse(item.getId());
                        int h = item.getQuantity() + 1;
                        System.out.println(h + "<"+t);
                        if (h<=t) {
//                            8.3.7. doPost() -update lại số lượng của sản phẩm
                            cart.update(item.getId(), item.getQuantity() + 1);
                        }else{
//                            8.3.7. doPost() -update lại số lượng của sản phẩm
                            cart.update(item.getId(), item.getQuantity());
                        }
                        exists = true;
                        break;
                    }
                }

                // Nếu chưa tồn tại, thêm sản phẩm mới
                if (!exists) {
                    newItem.setQuantity(1);
                    cart.add(newItem);
                }
            }
            total = cart.getTotal();
            session.setAttribute("valueOfPromotion", cart.getTotal());
            session.setAttribute("cart", cart);
        }
        // Tạo JSON trả về
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(gson.toJson(new CartResponse(cart.getListCartItem(), total, uName)));
    }

    private static class CartResponse {
        private final List<CartItem> lists;
        private final double totalPrice;
        private final String userName;

        public CartResponse(List<CartItem> lists, double totalPrice, String userName) {
            this.lists = lists;
            this.totalPrice = totalPrice;
            this.userName = userName;
        }

        public List<CartItem> getLists() {
            return lists;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public String getUserName() {
            return userName;
        }
    }
}
