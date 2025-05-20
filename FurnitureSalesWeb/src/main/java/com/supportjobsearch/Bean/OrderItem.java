package com.supportjobsearch.Bean;

import com.supportjobsearch.Product;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private int id;
    private int orderID;
    private int productID;
    private int amount;
    private Product product;
    private Order order;

    public OrderItem(int orderID, int productID, int amount) {
        this.orderID = orderID;
        this.productID = productID;
        this.amount = amount;
    }

//    public OrderItem(int id, int amount) {
//        this.id = id;
//        this.amount = amount;
//    }

    // Constructor for searching an existing OrderItem
    public OrderItem(int orderID, int productID) {
        this.orderID = orderID;
        this.productID = productID;
    }


    public OrderItem() {

    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderID=" + orderID +
                ", productID=" + productID +
                ", amount=" + amount +
                ", product=" + product +
                '}';
    }
}

