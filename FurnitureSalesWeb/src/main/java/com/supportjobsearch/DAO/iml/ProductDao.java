package com.example.ecommerce.DAO.iml;

import com.example.ecommerce.Bean.Product;
import com.example.ecommerce.Common.Enum.ProductFilter;
import com.example.ecommerce.Common.Exception.ProductNotFoundException;
import com.example.ecommerce.DAO.interf.IProductDAO;
import com.example.ecommerce.Database.JDBIConnect;
import com.example.ecommerce.Dto.ProductDto;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends ImplementBase implements IProductDAO {
    List<Product> productList = new ArrayList<>();

    public ProductDao() {
        super();
    }

    @Override
    public int recordSize() {
        return 0;
    }

    public Jdbi getJdbi() {
        return db.jdbi;
    }

    @Override
    public int addNewProduct(Product product) {
        return handle.createUpdate("INSERT INTO products (proName, price, description, thumb, created_at, cateID, atributeID) " +
                                "VALUES (:name, :price, :description, :thumb, :create_at, :cateID, :attributeID)")
                        .bind("name", product.getProName())
                        .bind("price", product.getPrice())
                        .bind("description", product.getDescription())
                        .bind("thumb", product.getThumb())
                        .bind("create_at", product.getCreated_at())
                        .bind("cateID", product.getCateID())
                        .bind("attributeID", product.getAtributeID())
                        .executeAndReturnGeneratedKeys("id").mapTo(Integer.class).one();
    }

    public void addProductToAllWarehouse(int productID){
        handle.createUpdate("""
                INSERT INTO having_product (productID, warehouseID, amount)
                        SELECT
                            :productID,
                            w.id,
                            0 AS amount
                        FROM
                            warehouse w
                        WHERE
                            NOT EXISTS (
                                SELECT 1
                                FROM having_product hp
                                WHERE hp.productID = :productID AND hp.warehouseID = w.id
                            )
                """).bind("productID", productID).execute();
    }

    @Override
    public Product getProductById(int id) {
        db = JDBIConnect.getInstance();
        return db.jdbi.withHandle(handle -> handle.createQuery("select * from products where id = :id")
                .bind("id", id)
                .mapToBean(Product.class).findOne().orElse(null));
    }

    @Override
    public List<Product> getAllProducts() {
        db = JDBIConnect.getInstance();
        return db.jdbi.withHandle(handle -> handle.createQuery("select * from products")
                .mapToBean(Product.class).list());
    }

    @Override
    public List<Product> get20ProductEach(int index) {
        return null;
    }



    @Override
    public boolean updateProduct(Product p) {
        log.info("Updating product: " + p);

        boolean result = handle.createUpdate("UPDATE products SET proName = ?, price = ?, description = ?, cateID = ?, atributeID = ? WHERE id = ?")
                .bind(0, p.getProName())
                .bind(1, p.getPrice())
                .bind(2, p.getDescription())
                .bind(3, p.getCateID())
                .bind(4, p.getAtributeID())
                .bind(5, p.getId())
                .execute() > 0;

        return result;
    }

    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
        System.out.println("running");
        dao.addProductToAllWarehouse(156);
    }
}
