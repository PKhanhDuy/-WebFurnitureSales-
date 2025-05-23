package com.supportjobsearch.DAO.iml;

import com.supportjobsearch.Bean.Warehouse;
import com.supportjobsearch.DAO.interf.IWarehouseDao;
import org.jdbi.v3.core.Jdbi;

import java.util.ArrayList;
import java.util.List;

public class WarehouseDao extends ImplementBase implements IWarehouseDao {
    List<Warehouse> warehouses = new ArrayList<>();

    public WarehouseDao() {
        super();
    }

    @Override
    public List<Warehouse> getAllWarehouse() {

        if (warehouses.isEmpty()) {
            warehouses = handle.createQuery("SELECT * FROM warehouse")
                    .mapToBean(Warehouse.class)
                    .list();
        }

        return warehouses;
    }

    public Jdbi getJdbi() {
        return db.jdbi;
    }

    @Override
    public List<Warehouse> getAllWarehouse(boolean force) {
        log.info("Querying all warehouses with force: " + force);

        if (force) {
            warehouses.clear();
        }
        warehouses = getAllWarehouse();

        return warehouses;
    }

    @Override
    public Warehouse getWarehouseById(int id) {
        return null;
    }

    @Override
    public boolean addWarehouse(Warehouse warehouse) {
        return false;
    }

    @Override
    public boolean updateNameById(int id, String name) {
        return false;
    }

    @Override
    public boolean updateManagerbyId(int id, int managerid) {
        return false;
    }

    @Override
    public boolean updateAddressById(int id, String address) {
        return false;
    }

    @Override
    public boolean updateStockById(int id, int stock) {
        return false;
    }

    @Override
    public boolean updateAvailableById(int id, String state) {
        return false;
    }

    @Override
    public boolean deleteWarehouseById(int id) {
        return false;
    }

    @Override
    public int totalProductItems() {
        log.info("Querying total product items");

        String sql = "SELECT COUNT(*) FROM products";

        // Use JDBI to execute the query and fetch the result
        return handle.createQuery(sql)
                .mapTo(Integer.class)
                .one();
    }

    public int getAmountProductInWarehouse(int productId) {
            return handle.createQuery("select sum(hp.amount) from having_product hp join warehouse w on hp.warehouseID = w.id where hp.productID = ? group by hp.productID")
                    .bind(0, productId)
                    .mapTo(Integer.class)
                    .findOne()
                    .orElse(0);
    }

    @Override
    public int totalInStock() {
        log.info("Querying total inStock");
        String sql = "SELECT COALESCE(SUM(amount), 0) FROM having_product WHERE amount > 0";

        return handle.createQuery(sql)
                .mapTo(Integer.class)
                .one();
    }

    @Override
    public int totalOutOfStock() {
        log.info("Querying total outOfStock");
        String sql = """
                    SELECT COUNT(DISTINCT p.id)
                    FROM products p
                    LEFT JOIN having_product hp ON p.id = hp.productID
                    GROUP BY p.id
                    HAVING COALESCE(SUM(hp.amount), 0) = 0
                """;

        return handle.createQuery(sql)
                .mapTo(Integer.class)
                .list()
                .size();
    }

    @Override
    public void updateStock(int id, int pId, int stock) {
        log.info("Updating warehouse " + id + " product " + pId + " with stock " + stock);
        handle.createUpdate("UPDATE having_product SET amount = amount + ? WHERE warehouseID = ? AND productID =?")
                .bind(0, stock)
                .bind(1, id)
                .bind(2, pId)
                .execute();
    }

}
