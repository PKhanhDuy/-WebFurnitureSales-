package com.supportjobsearch.Bean;

import java.io.Serializable;

public class ProductAttribute implements Serializable {
    private int id;
    private String material;
    private String size;

    public ProductAttribute(){}

    public ProductAttribute(int id, String material, String size) {
        this.id = id;
        this.material = material;
        this.size = size;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ProductAttribute {id=" + id + ", material=" + material + ", size=" + size + "}";
    }
}
