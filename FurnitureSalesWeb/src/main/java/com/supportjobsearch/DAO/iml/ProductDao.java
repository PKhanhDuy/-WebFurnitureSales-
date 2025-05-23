package com.supportjobsearch.DAO.iml;


import com.supportjobsearch.DAO.interf.IProductDAO;
import com.supportjobsearch.Product;
import com.supportjobsearch.database.JDBIConnect;
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
    public List<Product> getProductByCategory(int cateID) {
        db = JDBIConnect.getInstance();
        return db.jdbi.withHandle(handle -> handle.createQuery("select * from products where cateID = :cateID")
                .bind("cateID", cateID)
                .mapToBean(Product.class).list());
    }

    @Override
    public List<Product> search(String name) {
        return handle.createQuery("SELECT p.id, p .proName, p.description, p.price, p.thumb FROM products AS p JOIN product_atribute AS pa ON p.atributeID = pa.id WHERE pa.material LIKE :name")
                .bind("name", "%" + name + "%")
                .mapToBean(Product.class).list();
    }
    // 6.4 Truy vấn lấy kết quả tìm kiếm phù hợp
    @Override
    public List<Product> searchProduct(String name) {
        return db.getJdbi().withHandle(handle1 -> handle.createQuery("SELECT * FROM products WHERE proName LIKE :name")
                .bind("name", "%" + name + "%")
                .mapToBean(Product.class).list());
    }

    @Override
    public List<Product> get4NewProducts() {
        db = JDBIConnect.getInstance();
        return db.getJdbi().withHandle(handle ->
                handle.createQuery("select * from products limit 4")
                        .mapToBean(Product.class).list());
    }

//    @Override
//    public List<Product> getProductByFilter(ProductFilter filter) {
//        return List.of();
//    }

    public List<Product> get4ProductOfCate(int cateID) {
        return db.jdbi.withHandle(handle -> handle.createQuery("select * from products where cateID = :cateID limit 4")
                .bind("cateID", cateID)
                .mapToBean(Product.class).list());
    }


    @Override
    public List<Product> getProductByFilter(String sort, String material) {
        String query = "select p.id, p.proName,p.price,p.description, p.thumb,p.created_at,p.cateID,p.atributeID " +
                "from products p join product_atribute pa on p.atributeID = pa.id ";

        if ("Tất cả".equals(material)) {
            query += "where 1=1 or pa.material like ?";
        } else {
            query += "where pa.material like ? ";
        }
        // Thêm điều kiện sắp xếp
        if ("Thấp đến cao".equals(sort)) {
            query += "order by p.price asc;";
        } else if ("Cao đến thấp".equals(sort)) {
            query += "order by p.price desc;";
        } else if ("Mới nhất".equals(sort)) {
            query += "order by p.created_at desc;";
        } else {
            query += "order by p.id asc;"; // Sắp xếp mặc định
        }

        String finalQuery = query;
        return db.jdbi.withHandle(handle -> handle.createQuery(finalQuery)
                .bind(0, '%' + material + '%')
                .mapToBean(Product.class).list());
    }

    @Override
    public Product getProductByName(String productName) throws Exception {
        log.info("Querying product by name: " + productName);
        Product p = null;

        try {
            p = handle.createQuery("SELECT * FROM products WHERE proName = ?")
                    .bind(0, productName)
                    .mapToBean(Product.class).first();
        }
        catch (IllegalStateException e) {
            throw new Exception("Product not exits");
        }

        return p;
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
