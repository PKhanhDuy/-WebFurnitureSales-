package com.supportjobsearch.dto;

import java.text.NumberFormat;
import java.util.Locale;

public class WarehouseDto {
    private int id;
    private String name;
    private String address;
    private String manager;
    private String phone;
    private int stock;
    private int shipping;
    private double revenue;
    private String revenueF;

    @Override
    public String toString() {
        return "WarehouseDto{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", manager='" + manager + '\'' +
               ", phone='" + phone + '\'' +
               ", stock=" + stock +
               ", shipping=" + shipping +
               ", revenue=" + revenue +
               ", revenueF='" + revenueF + '\'' +
               '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
        NumberFormat format = NumberFormat.getInstance(Locale.ENGLISH);
        revenueF = format.format(revenue);
    }

    public String getRevenueF() {
        return revenueF;
    }

    public void setRevenueF(String revenueF) {
        this.revenueF = revenueF;
    }
}
