package com.supportjobsearch.Bean;

import java.io.Serializable;

public class Address implements Serializable {
    private int id;
    private String princible;
    private String street;
    private String fullAddress;

    public Address() {}
    public Address(int id, String princible, String street, String fullAddress) {
        this.id = id;
        this.princible = princible;
        this.street = street;
        this.fullAddress = fullAddress;
    }

    public int getId() {
        return id;
    }

    public String getPrincible() {
        return princible;
    }

    public String getStreet() {
        return street;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrincible(String princible) {
        this.princible = princible;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "fullAddress='" + fullAddress +
                ", street='" + street +
                ", princible='" + princible +
                ", id=" + id +
                '}';
    }
}
