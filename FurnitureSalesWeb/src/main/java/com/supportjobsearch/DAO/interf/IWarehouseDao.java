package com.supportjobsearch.DAO.interf;

import com.supportjobsearch.Bean.Warehouse;

import java.util.List;

public interface IWarehouseDao {
    //Lay tat ca cac warehouse
    List<Warehouse> getAllWarehouse();
    List<Warehouse> getAllWarehouse(boolean force);
    //Lay warehoust theo id
    Warehouse getWarehouseById(int id);
    //Them mot warehouse moi
    boolean addWarehouse(Warehouse warehouse);
    //cap nhat ten cua warehouse
    boolean updateNameById(int id,String name);
    //Cap nhat nguoi quan ly cua warehouse
    boolean updateManagerbyId(int id, int managerid);
    //cap nhat dia chi cua warehouse
    boolean updateAddressById(int id,String address);
    //Cap nhat so luong cua warehouse
    boolean updateStockById(int id, int stock);
    //cap nhat trang thai cua warehouse
    boolean updateAvailableById(int id, String state);
    //xoa warehouse
    boolean deleteWarehouseById(int id);

    int totalProductItems();
    int totalInStock();
    int totalOutOfStock();

    void updateStock(int id, int pId, int stock);
}
