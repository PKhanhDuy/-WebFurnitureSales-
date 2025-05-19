package com.supportjobsearch;

import com.example.ecommerce.Common.Enum.Gender;
import com.example.ecommerce.Common.Enum.StatusUser;
import com.example.ecommerce.InsertData;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;

public class User implements Serializable {
    private int id;
    private String username;
    private String fullName;
    private Gender gender;
    private String pass;
    private String email;
    private String phoneNum;
    private StatusUser statusUser;
    private LocalDateTime createUser;
    private String avatar;
    private int roleID;

    public User() {
    }

    public User(int id, String username, String fullName, Gender gender, String pass, String email, String phoneNum, StatusUser statusUser, LocalDateTime createUser, String avatar, int roleID) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.gender = gender;
        this.pass = pass;
        this.email = email;
        this.phoneNum = phoneNum;
        this.statusUser = statusUser;
        this.createUser = createUser;
        this.avatar = avatar;
        this.roleID = roleID;
    }
    public User(String username, String pass, String email){
        this.username = username;
        this.pass = pass;
        this.email = email;

        this.roleID = 1;
        this.createUser = LocalDateTime.now();
        this.statusUser = StatusUser.ENABLE;
        this.gender = Gender.MALE;
        this.fullName = "";
        this.phoneNum = "";
        this.avatar = "";
    }
    public User(String username, String fullName, String email, String password, int roleID, Gender gender){
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.pass = password;
        this.roleID = roleID;
        this.gender = gender;

        this.createUser = LocalDateTime.now();
        this.statusUser = StatusUser.ENABLE;
        this.phoneNum = "";
        this.avatar = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public StatusUser getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(StatusUser statusUser) {
        this.statusUser = statusUser;
    }

    public LocalDateTime getCreateUser() {
        return createUser;
    }

    public void setCreateUser(LocalDateTime createUser) {
        this.createUser = createUser;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Override
    public String toString() {
        return "User {id=" + id +
                ", username=" + username +
                ", fullName=" + fullName +
                ", gender=" + gender +
                ", pass=" + pass +
                ", email=" + email +
                ", phoneNum=" + phoneNum +
                ", statusUser=" + statusUser +
                ", createUser=" + createUser +
                ", avatar=" + avatar +
                ", roleID=" + roleID + "}";
    }

    public static void main(String[] args) {
        String username = "4";
        String fullname = "the empty";
        String gender = "male";
        String email = "empty@gmail.com";
        String phone = "0933718070";
        String pass = "4";
        String statusUser = "enable";
        String createDate = "2025-12-11 10:00:00";
        String avatar = "avatar1.png";
        int role = 4;

        String url = "jdbc:mysql://127.0.0.1:3306/ltw?useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            InsertData.insertUsers(conn, username, fullname, gender, pass, email, phone, statusUser, createDate, avatar, role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
