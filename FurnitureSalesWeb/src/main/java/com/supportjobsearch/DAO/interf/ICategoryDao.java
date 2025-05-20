package com.supportjobsearch.DAO.interf;

import com.example.ecommerce.Bean.Category;

import java.util.List;

public interface ICategoryDao {
    //lấy tất cả các category
    List<Category> getAllCategory();
    //Lấy category theo id
    Category getCategoryById(int id);
    //Thêm một category mới
    boolean addCategory(int id, String cateName);
    //cập nhật ten cua catagory theo id
    boolean updateName(int id, String newName);
    //Xóa một category
    boolean deleteCategory(int id);

    Category getCategoryByName(String name);
}
