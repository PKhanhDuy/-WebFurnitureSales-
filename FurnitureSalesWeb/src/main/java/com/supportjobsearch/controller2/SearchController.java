package com.supportjobsearch.controller2;

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

import java.util.*;
import java.io.IOException;

@WebServlet("/search")
public class SearchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            // 6.1.4 SearchController xử lý request để lấy từ khóa
            String keyword = req.getParameter("search-input");
            // 6.1.5 SearchController gọi ProductService để lấy danh sách sản phẩm phù hợp với từ khóa
            ProductService productService = new ProductService();
            List<Product> searchResults = productService.getSearchProduct(keyword);
            // 6.1.8 Sau khi nhận danh sách sản phẩm từ ProductService, SearchController thực hiện
            // xử lí phân trang cho kết quả tìm kiếm (32 sản phẩm / trang)
            int currentPage = req.getParameter("page") != null ?
                    Integer.parseInt(req.getParameter("page")) : 1;
            int itemsPerPage = 32;
            int start = (currentPage - 1) * itemsPerPage;
            int end = Math.min(start + itemsPerPage, searchResults.size());
            List<Product> pagedResults = searchResults.subList(start, end);
            // 6.1.9 SearchController chuẩn bị dữ liệu và chuyển hướng đến trang search-products.jsp
            req.setAttribute("products", pagedResults);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", (int) Math.ceil((double)searchResults.size()/itemsPerPage));
            req.setAttribute("searchKeyword", keyword);
            req.getRequestDispatcher("/search-products.jsp").forward(req, resp);
        } catch (Exception e) {
            // Exceptions: Xử lý ngoại lệ nếu có lỗi
            req.setAttribute("error", "Đã xảy ra lỗi khi tìm kiếm. Vui lòng thử lại.");
            e.printStackTrace();
        }

    }
}
