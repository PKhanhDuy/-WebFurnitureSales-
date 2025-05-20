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
            // Bước 6.2: Nhận từ khóa tìm kiếm từ request
            String keyword = req.getParameter("search-input");
            
            // Bước 6.4: Gọi ProductService để tìm kiếm
            ProductService productService = new ProductService();
            List<Product> searchResults = productService.getSearchProduct(keyword);
            
            // Bước 6.5: Xử lý phân trang (32 sản phẩm/trang)
            int currentPage = req.getParameter("page") != null ? 
                Integer.parseInt(req.getParameter("page")) : 1;
            int itemsPerPage = 32;
            int start = (currentPage - 1) * itemsPerPage;
            int end = Math.min(start + itemsPerPage, searchResults.size());
            List<Product> pagedResults = searchResults.subList(start, end);
            
            // Bước 6.5: Lấy danh sách danh mục để hiển thị
            CategoryService categoryService = new CategoryService();
            List<Category> categories = categoryService.getAllCategory();
            // Chia danh mục thành các cột (5 danh mục/cột)
            HashMap<Integer, List<Category>> mapCate = new HashMap<>();
            int catePerCol = 5;
            int countCol = categories.size() % catePerCol == 0 ? 
                categories.size() / catePerCol : 
                categories.size() / catePerCol + 1;
                
            for (int i = 0; i < countCol; i++) {
                int index = i * catePerCol;
                List<Category> colCategories = new ArrayList<>();
                for (int j = index; j < index + catePerCol && j < categories.size(); j++) {
                    colCategories.add(categories.get(j));
                }
                mapCate.put(i, colCategories);
            }
            
            // Chuẩn bị dữ liệu cho view
            req.setAttribute("products", pagedResults);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", (int) Math.ceil((double)searchResults.size()/itemsPerPage));
            req.setAttribute("mapCate", mapCate);
            req.setAttribute("searchKeyword", keyword); // Giữ lại từ khóa
            // Bước 6.6: Hiển thị kết quả
            req.getRequestDispatcher("/views/web/product/search-products.jsp").forward(req, resp);
            
        } catch (Exception e) {
            // Bước 8: Xử lý ngoại lệ nếu có lỗi
            req.setAttribute("error", "Đã xảy ra lỗi khi tìm kiếm. Vui lòng thử lại.");
            req.getRequestDispatcher("/views/web/error.jsp").forward(req, resp);
            e.printStackTrace();
        }
    }
}
