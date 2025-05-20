package com.supportjobsearch.DAO.interf;


import com.supportjobsearch.Product;

import java.util.List;

public interface IProductDAO {
    // Total records size in database
    int recordSize();

    public int addNewProduct(Product product);

    // Get product by id
    Product getProductById(int id);

    // Get all product
    List<Product> getAllProducts();

    // Get 20 product for each page
    List<Product> get20ProductEach(int index);

    // Get product by category
    List<Product> getProductByCategory(int cateId);

    // Get product using Search
    List<Product> search(String name);

    // get about 8 new products
    List<Product> get4NewProducts();

    public List<Product> searchProduct(String name);


    List<Product> getProductByFilter(String sort, String material);

    Product getProductByName(String productName) throws Exception;

    boolean updateProduct(Product p);
}
