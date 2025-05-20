package com.supportjobsearch.database;

import java.sql.*;

public class DBConnect {
    static String url = "jdbc:mysql://";
    static Connection conn;

    public static Statement get() throws SQLException, ClassNotFoundException {
        try{
            if(conn==null || conn.isClosed()) makeConnect();
            return conn.createStatement();
        }catch (SQLException | ClassNotFoundException e){
            return null;
        }
    }
    public static void makeConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Statement statement = get();
        ResultSet rs = statement.executeQuery("select * from products");
        while (rs.next()) {
            System.out.println(rs.getInt(1) +
                    ";" + rs.getString(2)+
                    ";" + rs.getDouble(4) +
                    ";" + rs.getString(3));
        }
    }}
