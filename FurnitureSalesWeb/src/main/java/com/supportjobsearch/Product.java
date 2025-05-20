package com.supportjobsearch;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;


public class Product implements Serializable {

    private int id;
    private String proName;
    private String description;
    private double price;
    private String thumb;
    private LocalDateTime created_at;
    private int cateID;
    private int atributeID;

    private String fullPrice;

    public Product() {
    }

    public Product(int id, String proName, String description, double price, String thumb, LocalDateTime created_at, int cateID, int atributeID) {
        this.id = id;
        this.proName = proName;
        this.description = description;
        this.price = price;
        this.thumb = thumb;
        this.created_at = created_at;
        this.cateID = cateID;
        this.atributeID = atributeID;
        NumberFormat formater = NumberFormat.getInstance(Locale.ENGLISH);
        fullPrice = formater.format(price);
    }

    public int getId() {
        return id;
    }

    public String getProName() {
        return proName;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getThumb() {
        return thumb;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public int getAtributeID() {
        return atributeID;
    }

    public String getFullPrice() {
        return fullPrice;
    }

    //    public void setFullPrice(String fullPrice)
    public int getCateID() {
        return cateID;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setAtributeID(int attributeId) {
        this.atributeID = attributeId;
    }

    public void setCateID(int cateId) {
        this.cateID = cateId;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", proName=" + proName +
                ", description=" + description +
                ", price=" + fullPrice +
                ", thumb=" + thumb +
                ", created_at=" + created_at +
                ",cateID=" + cateID +
                ", atributeID=" + atributeID +
                "}";
    }

    public static void main(String[] args) {
        String input = "1,000,000,000";
        input = input.replace(",", ""); // Remove commas
        long number = Long.parseLong(input); // Parse the string to a long
        System.out.println(number); // Output: 1000000000
    }
}
