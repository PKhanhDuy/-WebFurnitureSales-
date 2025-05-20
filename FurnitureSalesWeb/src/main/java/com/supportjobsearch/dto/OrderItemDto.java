package com.supportjobsearch.dto;

import java.text.NumberFormat;
import java.util.Locale;

public class OrderItemDto {
    private String productName;
    private int amount;
    private double price;
    private String priceF;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "OrderItemDto{" +
               "productName='" + productName + '\'' +
               ", amount=" + amount +
               ", price=" + price +
               ", priceF='" + priceF + '\'' +
               '}';
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        priceF = formatter.format(price);
    }

    public String getPriceF() {
        return priceF;
    }

    public void setPriceF(String priceF) {
        this.priceF = priceF;
    }
}
