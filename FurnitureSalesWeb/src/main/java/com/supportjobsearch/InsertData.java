package com.supportjobsearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


public class InsertData {
    static String p = "D:\\LTW\\LTW_project\\Ecommerce_Final\\src\\main\\webapp\\public\\json\\";

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        String jsonFilePath = "D:\\LTW\\LTW_project\\Ecommerce_Final\\src\\main\\webapp\\public\\json\\product-details.json";

        try {
            // Read the JSON file
            String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray products = new JSONArray(content);

            jsonFilePath = p + "role.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray roles = new JSONArray(content);

            jsonFilePath = p + "permission.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray permissions = new JSONArray(content);

            jsonFilePath = p + "assigning_role.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray assigning_roles = new JSONArray(content);

            jsonFilePath = p + "user.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray users = new JSONArray(content);

            jsonFilePath = p + "address.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray addresses = new JSONArray(content);

            jsonFilePath = p + "own_address.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray own_addresses = new JSONArray(content);

            jsonFilePath = p + "warehouse.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray warehouses = new JSONArray(content);

            jsonFilePath = p + "rating.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray ratings = new JSONArray(content);

            jsonFilePath = p + "promotion.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray promotions = new JSONArray(content);

            jsonFilePath = p + "favourite.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray favourites = new JSONArray(content);

            jsonFilePath = p + "payment.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray payments = new JSONArray(content);

            jsonFilePath = p + "order.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray orders = new JSONArray(content);

            jsonFilePath = p + "order_item.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray orderItems = new JSONArray(content);

            jsonFilePath = p + "having-product.json";
            content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONArray havingProducts = new JSONArray(content);

            // Database connection
            String url = "jdbc:mysql://127.0.0.1:3306/ltw?useUnicode=true&characterEncoding=utf-8";
            String user = "root";
            String password = "";

            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(url, user, password)) {

                System.out.println("Connected to database ltw");

                // product
                for (int i = 0; i < products.length(); i++) {
                    System.out.println("Added product: " + (i+1));

                    JSONObject product = products.getJSONObject(i);

                    String title = product.getString("title");
                    double price = parsePrice(product.getString("price"));
                    String description = product.optString("description", "");
                    String thumb = "public/images/all-products/" + (i+1) + ".jpg"; // Add your logic for thumbnails
                    String category = extractCategory(product.getString("category"));
                    String[] attributes = parseAttributes(product.getString("attribute"));
                    String material = attributes[0];
                    String size = attributes[1];

                    int categoryId = insertCategory(conn, category);
                    int attributeId = insertAttribute(conn, material, size);

                    insertProduct(conn, title, price, description, thumb, categoryId, attributeId);
                }
                // role
                for (int i = 0; i < roles.length(); i++) {

                    JSONObject role = roles.getJSONObject(i);
                    String nameRole = role.getString("nameRole");

                    insertRole(conn, nameRole);
                }
                // permission
                for (int i = 0; i < permissions.length(); i++) {
                    JSONObject permission = permissions.getJSONObject(i);
                    String permissionName = permission.getString("permissionName");
                    String createDate = permission.getString("createDate");
                    String lastUpdate = permission.getString("lastUpdate");

                    insertPermission(conn, permissionName, createDate, lastUpdate);
                    System.out.println("Permission " + permissionName + " added");
                }

                // assigning_role
                for (int i = 0; i < assigning_roles.length(); i++) {
                    JSONObject assigning_role = assigning_roles.getJSONObject(i);

                    int permission_id = assigning_role.getInt("permissionID");
                    int role_id = assigning_role.getInt("roleID");

                    insertAssigningRole(conn, permission_id, role_id);
                    System.out.println("Assigning role " + role_id + " permission " + permission_id);
                }

                // user
                for (int i = 0; i < users.length(); i++) {
                    JSONObject userr = users.getJSONObject(i);

                    String username = userr.getString("username");
                    String fullname = userr.getString("fullName");
                    String gender = userr.getString("gender");
                    String email = userr.getString("email");
                    String phone = userr.getString("phoneNum");
                    String pass = userr.getString("pass");
                    String statusUser = userr.getString("statusUser");
                    String createDate = userr.getString("createUser");
                    String avatar = userr.getString("avatar");
                    int role = userr.getInt("roleID");

                    insertUsers(conn, username, fullname, gender, pass, email, phone, statusUser, createDate, avatar, role);
                    System.out.println("Inserted " + username + " in database");
                }
                // address
                for (int i = 0; i < addresses.length(); i++) {
                    JSONObject address = addresses.getJSONObject(i);
                    String Princible = address.getString("Princible");
                    String Street = address.getString("Street");
                    String FullAddress = address.getString("FullAddress");

                    insertAddress(conn, Princible, Street, FullAddress);
                    System.out.println("Added address: " + FullAddress);
                }

                // own_address
                for (int i = 0; i < own_addresses.length(); i++) {
                    JSONObject own_address = own_addresses.getJSONObject(i);
                    int userID = own_address.getInt("uID");
                    int addressID = own_address.getInt("aID");

                    insertOwnAddress(conn, userID, addressID);
                    System.out.println(own_address);
                }
                // warehouse
                for (int i = 0; i < warehouses.length(); i++) {
                    JSONObject warehouse = warehouses.getJSONObject(i);
                    String wareHouseName = warehouse.getString("wareHouseName");
                    int managerID = warehouse.getInt("managerID");
                    int addressID = warehouse.getInt("addressID");
                    int stock = warehouse.getInt("stock");
                    String availability = warehouse.getString("available");

                    insertWarehouse(conn, wareHouseName, managerID, addressID, stock, availability);
                    System.out.println("Warehouse " + wareHouseName + " added");
                }

                // rating
                for (int i = 0; i < ratings.length(); i++) {
                    JSONObject rating = ratings.getJSONObject(i);

                    int userID = rating.getInt("userID");
                    int productID = rating.getInt("productID");
                    int stars = rating.getInt("stars");
                    String commentRate  = rating.getString("commentRate");
                    String dateRate = rating.getString("dateRate");

                    InsertRating(conn, userID,productID, stars, commentRate,dateRate);
                    System.out.println("Inserted rating: " + userID + " " + productID + " " + stars + " " + commentRate + " " + dateRate);
                }

                // promotion
                for (int i = 0; i < promotions.length(); i++) {
                    JSONObject promotion = promotions.getJSONObject(i);
                    String codes = promotion.getString("codes");
                    String type = promotion.getString("type");
                    int limitPro = promotion.getInt("limitPro");
                    double valueOfPro = promotion.getDouble("valueOfPro");
                    String statusOfPro = promotion.getString("statusOfPro");
                    String startDate = promotion.getString("startDate");
                    String endDate = promotion.getString("endDate");

                    insertPromotion(conn, codes, type, limitPro, valueOfPro, statusOfPro, startDate, endDate);
                    System.out.println("Inserted promotion " + codes);
                }

                // favourite
                for (int i = 0; i < favourites.length(); i++) {
                    JSONObject favourite = favourites.getJSONObject(i);
                    int productID = favourite.getInt("productID");
                    int userID = favourite.getInt("userID");

                    insertFavourite(conn, productID, userID);
                    System.out.println("Inserted favourite" + favourite);
                }

                // payment
                for (int i = 0; i < payments.length(); i++) {
                    JSONObject payment = payments.getJSONObject(i);
                    String method = payment.getString("method");
                    String statuss = payment.getString("statuss");

                    insertPayment(conn, method, statuss);
                    System.out.println("Payment " + method + " inserted into database");
                }

                // order
                for (int i = 0; i < orders.length(); i++) {
                    JSONObject order = orders.getJSONObject(i);
                    int userID = order.getInt("userID");
                    int paymentID = order.getInt("paymentID");
                    String shippingStatus = order.getString("shippingStatus");
                    String createDate = order.getString("createDate");
                    String phoneNo = order.getString("phoneNo");
                    int promotion_code = order.getInt("promotion_code");

                    insertOrders(conn, userID, paymentID, shippingStatus, createDate, phoneNo, promotion_code);
                    System.out.println("Added order by userID: " + userID + ", paymentID: " + paymentID + ", shippingStatus: " + shippingStatus);
                }

                // order item
                for (int i = 0; i < orderItems.length(); i++) {
                    JSONObject orderItem = orderItems.getJSONObject(i);
                    int oderID = orderItem.getInt("orderID");
                    int productID = orderItem.getInt("productID");
                    int amount = orderItem.getInt("amount");

                    insertOrderItems(conn, orderItem, productID, amount);
                    System.out.println("Inserted order item " + orderItem);
                }

                // having product
                for (int i = 0; i < havingProducts.length(); i++){
                    JSONObject havingProduct = havingProducts.getJSONObject(i);
                    int productID = havingProduct.getInt("productID");
                    int warehouseID = havingProduct.getInt("warehouseID");
                    int amount = havingProduct.getInt("amount");

                    insertHavingProduct(conn, warehouseID, productID, amount);
                    System.out.println("Added having product " + productID + " with amount " + amount);
                }

            } catch (SQLException e) {
                System.out.println("Can not connect to database, please check your connection or url.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Disconnected from database");

    }

    private static void insertHavingProduct(Connection conn, int warehouseID, int productID, int amount) {
        String sql = "insert into having_product values(?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, warehouseID);
            ps.setInt(2, productID);
            ps.setInt(3, amount);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertOrderItems(Connection conn, JSONObject orderItem, int productID, int amount) {
        String sql = "insert into order_item(orderID, productID, amount) values(?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, orderItem.getInt("orderID"));
            ps.setInt(2, productID);
            ps.setInt(3, amount);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertOrders(Connection conn, int userID, int paymentID, String shippingStatus, String createDate, String phoneNo, int promotionCode) {
        String sql = promotionCode != 0 ?
                "insert into orders(userID, paymentID, shippingStatus, createDate, sdt, promotion_id) values(?,?,?,?,?,?)" :
                "insert into orders(userID, paymentID, shippingStatus, createDate, sdt) values(?,?,?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, userID);
            ps.setInt(2, paymentID);
            ps.setString(3, shippingStatus);
            ps.setString(4, createDate);
            ps.setString(5, phoneNo);
            if(promotionCode != 0)
                ps.setInt(6, promotionCode);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertPayment(Connection conn, String method, String statuss) {
        String sql = "insert into payment (method, statuss) values (?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, method);
            ps.setString(2, statuss);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertFavourite(Connection conn, int productID, int userID) {
        String sql = "insert into favourite_products values(?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, productID);
            ps.setInt(2, userID);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertPromotion(Connection conn, String codes, String type, int limitPro, double valueOfPro, String statusOfPro, String startDate, String endDate) {
        String sql = "INSERT INTO promotions(codes, type, limitPro, valueOfPro, statusOfPro, startDate, endDate) VALUES(?,?,?,?,?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, codes);
            ps.setString(2, type);
            ps.setInt(3, limitPro);
            ps.setDouble(4, valueOfPro);
            ps.setString(5, statusOfPro);
            ps.setString(6, startDate);
            ps.setString(7, endDate);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void InsertRating(Connection conn, int userID, int productID, int stars, String commentRate, String dateRate) {
        String sql = "insert into ratings values(?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, userID);
            ps.setInt(2, productID);
            ps.setInt(3, stars);
            ps.setString(4, commentRate);
            ps.setString(5, dateRate);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertWarehouse(Connection conn, String wareHouseName, int managerID, int addressID, int stock, String availability) {
        String sql = "insert into warehouse(wareHouseName, managerID, addressID, stock, available) values(?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, wareHouseName);
            ps.setInt(2, managerID);
            ps.setInt(3, addressID);
            ps.setInt(4, stock);
            ps.setString(5, availability);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertOwnAddress(Connection conn, int userID, int addressID) {
        String sql = "insert into own_address values(?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, userID);
            ps.setInt(2, addressID);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertAddress(Connection conn, String princible, String street, String fullAddress) {
        String sql = "INSERT INTO address(Princible, Street, FullAddress) VALUES(?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, princible);
            ps.setString(2, street);
            ps.setString(3, fullAddress);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertUsers(Connection conn, String username, String fullname, String gender, String pass, String email, String phone, String statusUser, String createDate, String avatar, int role) {
        pass = hashPassword(pass);
        String sql = "INSERT INTO users" +
                "(username, fullName, gender, pass, email, phoneNum, statusUser, createUser, avatar, roleID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, username);
            ps.setString(2, fullname);
            ps.setString(3, gender);
            ps.setString(4, pass);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, statusUser);
            ps.setString(8, createDate);
            ps.setString(9, avatar);
            ps.setInt(10, role);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertAssigningRole(Connection conn, int permissionId, int roleId) {
        String sql = "insert into asigning_role(permissionID, roleID) values(?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, permissionId);
            ps.setInt(2, roleId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertPermission(Connection conn, String name, String createDate, String lastUpdate) {
        String sql = "INSERT INTO permission (permissionName, createDate, lastUpdate) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, name);
            ps.setString(2, createDate);
            ps.setString(3, lastUpdate);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertRole(Connection conn, String nameRole) {
        String sql = "insert into role (nameRole) values (?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, nameRole);
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static double parsePrice(String priceString) {
        priceString = priceString.replace("₫", "").replace(",", "").trim();
        return Double.parseDouble(priceString);
    }

    private static String extractCategory(String category) {
        return category.replace("Danh mục: ", "").trim();
    }

    private static String[] parseAttributes(String attribute) {
        String material = null;
        String size = null;

        if (attribute.contains("mm")) {
            size = attribute;
        } else {
            material = attribute;
        }

        return new String[]{material, size};
    }

    private static int insertCategory(Connection conn, String categoryName) throws SQLException {

        String check = "SELECT * FROM category WHERE cateName = '" + categoryName + "'";
        try (PreparedStatement ps = conn.prepareStatement(check)) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("id");
            }
        }

        String sql = "INSERT INTO category (cateName) VALUES (?) ON DUPLICATE KEY UPDATE ID=LAST_INSERT_ID(ID)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, categoryName);
            stmt.executeUpdate();

            var rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

    private static int insertAttribute(Connection conn, String material, String size) throws SQLException {
        String sql = "INSERT INTO product_atribute (material, size) VALUES (?, ?) ON DUPLICATE KEY UPDATE ID=LAST_INSERT_ID(ID)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, material);
            stmt.setString(2, size);
            stmt.executeUpdate();

            var rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

    private static void insertProduct(Connection conn, String title, double price, String description, String thumb, int categoryId, int attributeId) throws SQLException {
        String sql = "INSERT INTO products (proName, price, description, thumb, created_at, cateID, atributeID) VALUES (?, ?, ?, ?, NOW(), ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setDouble(2, price);
            stmt.setString(3, description);
            stmt.setString(4, thumb);
            stmt.setInt(5, categoryId);
            stmt.setInt(6, attributeId);
            stmt.executeUpdate();
        }
    }


}
