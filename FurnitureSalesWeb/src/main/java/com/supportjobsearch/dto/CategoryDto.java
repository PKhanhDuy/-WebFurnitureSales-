package com.supportjobsearch.dto;

import java.text.NumberFormat;
import java.util.Locale;

public class CategoryDto {
    private int categoryid;
    private String category_name;
    private double starting_price;
    private String priceF;
    private int product_stock;
    private String thumb;

    @Override
    public String toString() {
        return "CategoryDto{" +
               "categoryid=" + categoryid +
               ", category_name='" + category_name + '\'' +
               ", starting_price=" + starting_price +
               ", priceF='" + priceF + '\'' +
               ", product_stock=" + product_stock +
               ", thumb='" + thumb + '\'' +
               '}';
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public double getStarting_price() {
        return starting_price;
    }

    public void setStarting_price(double starting_price) {
        this.starting_price = starting_price;
        NumberFormat format = NumberFormat.getInstance(Locale.ENGLISH);
        priceF = format.format(starting_price);
    }

    public String getPriceF() {
        return priceF;
    }

    public void setPriceF(String priceF) {
        this.priceF = priceF;
    }

    public int getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(int product_stock) {
        this.product_stock = product_stock;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
