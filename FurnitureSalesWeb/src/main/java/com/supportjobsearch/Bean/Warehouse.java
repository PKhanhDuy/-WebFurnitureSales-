package com.supportjobsearch.Bean;

import java.io.Serializable;

public class Warehouse implements Serializable {
    private int id;
    private String wareHouseName;
    private int managerID;
    private int addressID;
    private int stock;
    private String available;

    public Warehouse() {}

    public Warehouse(int id, String wareHouseName, int managerID, int addressID, int stock, String available) {
        this.id = id;
        this.wareHouseName = wareHouseName;
        this.managerID = managerID;
        this.addressID = addressID;
        this.stock = stock;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Warehouse {id=" + id
                + ", wareHouseName=" + wareHouseName
                + ", managerID=" + managerID
                + ", addressID=" + addressID
                + ", stock=" + stock
                + ", available=" + available + "}";
    }
}
