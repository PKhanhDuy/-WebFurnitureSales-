package com.supportjobsearch.controller2;

import com.supportjobsearch.Bean.User;
import com.supportjobsearch.Cart;
import com.supportjobsearch.Category;
import com.supportjobsearch.Product;
import com.supportjobsearch.service.CategoryService;
import com.supportjobsearch.service.ProductService;
import jakarta.servlet.RequestDispatcher;
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

@WebServlet(name = "home", value = "/kenes")
public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = null;
        HttpSession session = request.getSession(true);
        CategoryService cateService = CategoryService.getInstance();
        List<Category> categories = new ArrayList<>();

        Cart c = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("auth");


        if (c == null) {
            c = new Cart();
            session.setAttribute("cart", c);
        }

        try {
            products = ProductService.getInstance().getNew4Products();
            categories = cateService.getAllCategory();
            if (products == null || products.isEmpty()) {
                request.setAttribute("error", "No product found");
            } else {
                request.setAttribute("success", "ddmmm");
                request.setAttribute("list_product", products);
                System.out.println(products);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "failed to load product");
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
        request.setAttribute("mapCate", mapCate);

        if (user != null) {
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                try {
                    int productId = Integer.parseInt(idParam);
                    Product product = ProductService.getInstance().getProductById(productId);
                    this.updateRecentlyViewedProducts(session, product);
                } catch (NumberFormatException e) {
                    log("Invalid product ID format", e);
                    request.setAttribute("error", "Invalid product ID.");
                }
            }else {
                System.out.println("Không có tham số 'id' được truyền.");
            }

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void updateRecentlyViewedProducts(HttpSession session, Product product) {
        if (product != null) {
            List<Product> recentlyViewed = (List<Product>) session.getAttribute("recentlyView");
            System.out.println("recently view" + recentlyViewed);
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
        }
        System.out.println("khong co san pham nao ow day");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
