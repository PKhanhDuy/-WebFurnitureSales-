package com.supportjobsearch.service;

import com.supportjobsearch.Bean.ProductAttribute;
import com.supportjobsearch.DAO.iml.ProductAttributeDao;
import com.supportjobsearch.DAO.iml.ProductDao;
import com.supportjobsearch.Product;
import com.supportjobsearch.service.ServiceBase;
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

    public List<Product> getSearch(String name) {
        return productDao.search(name);
    }
    // Bước 6.4: Lấy danh sách sản phẩm phù hợp với từ khóa
    public List<Product> getSearchProduct(String name) {
        return productDao.searchProduct(name);
    }

    public List<ProductDto> getAllProductsDto() {
        try {
            var j = productDao.getJdbi();
            j.installPlugin(new SqlObjectPlugin());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productDao.getJdbi().withExtension(IProductDTO.class, IProductDTO::getAllProducts);
    }

    public List<JSONObject> convertToJson(List<ProductDto> products) {
        List<JSONObject> li = new ArrayList<>();

        NumberFormat formater = NumberFormat.getInstance(Locale.ENGLISH);


        products.forEach(p -> {
            JSONObject j = new JSONObject();
            JSONObject product = new JSONObject();
            product.put("id", p.getId());
            product.put("name", p.getName());
            var fullPrice = formater.format(p.getPrice());
            product.put("price", fullPrice);
            product.put("thumb", p.getThumb());
            product.put("stock", p.getStock());
            product.put("category", p.getCategory());
            product.put("averageRating", p.getAverageRating());
            product.put("totalReviews", p.getTotalReviews());
            li.add(product);
        });

        return li;
    }

    public List<Product> getProductByFilter(String sort, String material) {
        return productDao.getProductByFilter(sort, material);
    }

    public Product getProductByName(String productName) {
        log.info("ProductService getProductByName...");

        Product p = null;
        try {
            p = productDao.getProductByName(productName);
        }
        catch (ProductNotFoundException e){
            return null;
        }

        return p;
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

