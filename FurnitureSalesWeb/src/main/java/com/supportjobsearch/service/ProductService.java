package com.example.ecommerce.service;

import com.example.ecommerce.Bean.Product;
import com.example.ecommerce.Bean.ProductAttribute;
import com.example.ecommerce.Common.Exception.ProductNotFoundException;
import com.example.ecommerce.DAO.iml.ProductAttributeDao;
import com.example.ecommerce.DAO.iml.ProductDao;
import com.example.ecommerce.DAO.interf.IProductDTO;
import com.example.ecommerce.Dto.ProductDto;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.*;

public class ProductService extends ServiceBase {
    private static ProductService instance;
    static Map<String, String> data = new HashMap<>();
    private ProductDao productDao = new ProductDao();
    private ProductAttributeDao paDao;

    public ProductService() {
        super();
        instance = this;
    }

    @Override
    public void init() {
        log.info("ProductService init...");
        productDao = new ProductDao();
        paDao = new ProductAttributeDao();
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
            instance.productDao = new ProductDao();
        }
        return instance;
    }

    public boolean addNewProduct(Product p) {
        log.info("ProductService addNewProduct...");
        int pid = productDao.addNewProduct(p);
        productDao.addProductToAllWarehouse(pid);
        return true;
    }

    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }

    public List<Product> getProductByCategory(int cateID) {
        return productDao.getProductByCategory(cateID);
    }

    public List<Product> getNew4Products() {
        return productDao.get4NewProducts();
    }

    public List<Product> get4ProductOfCate(int cateID) {
        return productDao.get4ProductOfCate(cateID);
    }

    public ProductAttribute addProductAttribute(ProductAttribute pa){
        log.info("ProductService addProductAttribute...");
        return paDao.addAttribute(pa);
    }

    public static void main(String[] args) {
        var a = new ProductService();
        a.init();

        var b = a.getAllProductsDto();
        var c = a.convertToJson(b);

        c.forEach(System.out::println);
    }

    public ProductAttribute getProductAttributeByID(int atributeID) {
        log.info("ProductService getProductAttributeByID...");
        return paDao.getById(atributeID);
    }

    public boolean updateProduct(Product p) {
        log.info("ProductService updateProduct...");
        return productDao.updateProduct(p);
    }

    public boolean updateAttribute(ProductAttribute a) {
        log.info("ProductService updateAttribute...");
        return paDao.updateMaterial(a.getId(), a.getMaterial()) && paDao.updateSize(a.getId(),a.getSize());
    }
}

