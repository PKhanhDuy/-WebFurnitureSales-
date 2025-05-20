package com.supportjobsearch;

public class CartItem {
    private int id;
    private String name;
    private String img;
    private double price;
    private int quantity;
    public CartItem(int id, String name, String img, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.quantity = quantity;
    }
    public CartItem(){

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }

//    8.3.9. setQuantity() - update lại số lượng-
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
