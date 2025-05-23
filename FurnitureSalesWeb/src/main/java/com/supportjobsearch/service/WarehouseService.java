package com.supportjobsearch.service;

import com.supportjobsearch.DAO.iml.WarehouseDao;
import com.supportjobsearch.DAO.interf.IWarehouseDTO;
import com.supportjobsearch.dto.WarehouseDto;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class WarehouseService extends ServiceBase {

    WarehouseDao warehouseDao;

    public static WarehouseService instance;

    public static WarehouseService getInstance() {
        if (instance == null) {
            instance = new WarehouseService();
            instance.warehouseDao = new WarehouseDao();
        }
        return instance;
    }

    WarehouseService() {
        super();
    }

    @Override
    public void init() {
        log.info("WarehouseService init...");
        warehouseDao = new WarehouseDao();
    }

    public int getTotalWarehouse(boolean forceUpdate) {
        log.info("WarehouseService getTotalWarehouse...");
        var wh = warehouseDao.getAllWarehouse(forceUpdate);

        return wh.size();
    }

    public List<WarehouseDto> getAllWarehouse() {
        log.info("WarehouseService getAllWarehouse...");
        var j = warehouseDao.getJdbi();
        j.installPlugin(new SqlObjectPlugin());
        var li = j.withExtension(IWarehouseDTO.class, IWarehouseDTO::getWarehouse);
        return li;
    }

    public int totalProductItems() {
        return warehouseDao.totalProductItems();
    }

    public int totalInStock() {
        return warehouseDao.totalInStock();
    }

    public int totalOutOfStock() {
        return warehouseDao.totalOutOfStock();
    }
    public int getAmountProductInWarehouse(int productId){
        return warehouseDao.getAmountProductInWarehouse(productId);
    }

    public static void main(String[] args) {
        var a = new WarehouseService();
        a.init();
        var b = a.getAllWarehouse();
        b.forEach(System.out::println);
    }

    public void updateStock(int id, int pId, int stock) {
        log.info("WarehouseService updateStock...");
        try {
            warehouseDao.updateStock(id, pId, stock);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
