package com.supportjobsearch.controller2;

import com.supportjobsearch.Bean.User;
import com.supportjobsearch.Cart;
import com.supportjobsearch.Category;
import com.supportjobsearch.Product;
import com.supportjobsearch.service.CategoryService;
import com.supportjobsearch.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "listProduct", value = "/list-product")
public class ListProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ProductService service = new ProductService();
        CategoryService cateService = new CategoryService();

        List<Product> data;
        List<Category> categories;
        try {
            data = service.getAllProducts();
            categories = cateService.getAllCategory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int itemsPerPage = 32;
        int currentPage = 1;

        //Lay du lieu category de hien thi len giao dien
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

        // Lấy tham số "page" từ URL
        String pageParam = req.getParameter("page");

        if (pageParam != null) {
            currentPage = Integer.parseInt(pageParam);
        }
        // Xác định phạm vi sản phẩm
        int start = (currentPage - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, data.size());

        List<Product> pageProducts = data.subList(start, end);

        // Tổng số trang
        int totalPages = (int) Math.ceil((double) data.size() / itemsPerPage);

        // Gửi dữ liệu tới JSP
        HttpSession session = req.getSession(true);
        Cart c = (Cart) session.getAttribute("cart");
        if (c == null) {
            c = new Cart();
            session.setAttribute("cart", c);
        }
        session.setAttribute("cart", c);

        req.setAttribute("products", pageProducts);
        req.setAttribute("currentPage", (Integer) currentPage);
        req.setAttribute("totalPages", (Integer) totalPages);
        req.setAttribute("mapCate", mapCate);
        User user = (User) session.getAttribute("auth");
        if (user != null) {
//            resp.sendRedirect("/list-product");
            Integer productId = (Integer) session.getAttribute("pID");
            System.out.println("id " + productId);

            if (productId != null) {
                try {
                    Product product = service.getProductById(productId);
                    System.out.println("dcm");
                    System.out.println(product);
                    if (product != null) {
                        updateRecentlyViewedProducts(session, product);
                    }
                } catch (NumberFormatException e) {
                    log("Invalid product ID format", e);
                }
            }

            req.getRequestDispatcher("/All-products.jsp").forward(req, resp);
        }
    }

    private void updateRecentlyViewedProducts(HttpSession session, Product product) {
        if (product != null) {
            List<Product> recentlyViewed = (List<Product>) session.getAttribute("recentlyView");
            System.out.println(recentlyViewed);
            if (recentlyViewed == null) {
                recentlyViewed = new ArrayList<>();
            }

            if (recentlyViewed.stream().noneMatch(p -> p.getId() == product.getId())) {
                recentlyViewed.add(0, product);
                if (recentlyViewed.size() > 8) {
                    recentlyViewed.remove(recentlyViewed.size() - 1);
                }
            }

            session.setAttribute("recentlyView", recentlyViewed);
            System.out.println("recentlyView: " + session.getAttribute("recentlyView"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        System.out.println("Received JSON: " + json);

        JSONObject jsonObject = new JSONObject(json);

        Integer productId = jsonObject.getInt("id");
        Integer attributeId = jsonObject.getInt("attributeId");
        Integer cateId = jsonObject.getInt("cateId");


        saveToSession(req, productId, attributeId, cateId);

//        Integer productId = (Integer) session.getAttribute("pID");
//        Integer attributeId = (Integer) session.getAttribute("aId");
//        Integer cateId = (Integer) session.getAttribute("cId");
//
//        System.out.println("Product ID: " + productId);
//        System.out.println("Attribute ID: " + attributeId);
//        System.out.println("Category ID: " + cateId);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", "Product details saved successfully");
        responseJson.put("status", "success");

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(responseJson.toString());

    }

    private void saveToSession(HttpServletRequest req, Integer productId, Integer attributeId, Integer cateId) {
        HttpSession session = req.getSession(true);
        session.setAttribute("pID", productId);
        session.setAttribute("aId", attributeId);
        session.setAttribute("cId", cateId);


    }

}
