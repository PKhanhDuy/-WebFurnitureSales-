package com.supportjobsearch.Bean;

import com.supportjobsearch.User;

import java.io.Serializable;

public class OwnAddress implements Serializable {
    private int userID;
    private int addressID;
    private User user;
    private Address address;


    public OwnAddress(int userID, int addressID) {
        this.userID = userID;
        this.addressID = addressID;
        this.user = new User();
        this.address = new Address();
    }

    public OwnAddress(){

    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OwnAddress{" +
                "userID=" + userID +
                ", addressID=" + addressID +
                ", user=" + user +
                ", address=" + address +
                '}';
    }
}
