package com.supportjobsearch.DAO.iml;

import com.supportjobsearch.Bean.Order;
import com.supportjobsearch.Bean.OrderItem;
import com.supportjobsearch.Bean.Payment;
import com.supportjobsearch.Bean.Promotion;
import com.supportjobsearch.enums.ShippingStatus;
import com.supportjobsearch.enums.Statuss;
import com.supportjobsearch.DAO.interf.IOrderDao;
import com.supportjobsearch.database.JDBIConnect;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Or;
import org.jdbi.v3.core.Jdbi;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class OrderDao extends ImplementBase implements IOrderDao {

    List<Order> allOrders = new ArrayList<>();

    public OrderDao() {
        super();
    }

    public Jdbi getJdbi(){
        return db.jdbi;
    }

    public List<Order> getAllOrders() {
        if (allOrders.isEmpty()) {
            allOrders = handle
                    .createQuery("SELECT * FROM orders")
                    .mapToBean(Order.class)
                    .list();
        }
        return allOrders;
    }

    public List<Order> getAllOrders(boolean force) {
        log.info("Query all orders with force: " + force);

        if (!force) allOrders = getAllOrders();
        else
            allOrders.clear();

        return allOrders;
    }

    @Override
    public int recordSize() {
        return allOrders.size();
    }

    @Override
    public Order addOrder(Order order) {
        Payment payment = new Payment();
        return db.getJdbi().withHandle(handle -> {
            int id = handle.createUpdate("INSERT INTO orders (userID, paymentID, shippingStatus, createDate, sdt, promotion_id) " +
                            "VALUES (:userID, :paymentID, :shippingStatus, :createDate, :sdt, :promotion_id)")
                    .bind("userID", order.getUserID())
                    .bind("paymentID", 1)
                    .bind("shippingStatus", ShippingStatus.Packaging)
                    .bind("createDate", LocalDateTime.now())
                    .bind("sdt", Optional.ofNullable(null))
                    .bind("promotion_id", Optional.ofNullable(null))
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .one();
            order.setId(id);
            return order;
        });
    }


    @Override
    public Order updateOrderByID(int id, int orderID, int productID, int amount) {
        return db.getJdbi().withHandle(handle ->
                handle.createUpdate("UPDATE orderItem SET orderID = :orderID, productID = :productID, amount = :amount  WHERE id = :id")
                        .bind("id", id)
                        .bind("orderID", orderID)
                        .bind("productID", orderID)
                        .bind("amount", amount)
                        .executeAndReturnGeneratedKeys("id")
                        .mapToBean(Order.class)
                        .findOne()
                        .orElseThrow(() -> new IllegalArgumentException("Order not found"))
        );
    }

    @Override
    public boolean deleteOrder(int id) {
        return db.getJdbi().withHandle(handle ->
                handle.createUpdate("DELETE FROM orderItem WHERE id = ?")
                        .bind(0, id)
                        .execute() > 0
        );
    }

    @Override
    public Order getOrderById(int id) {
        return db.getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM orders WHERE id = ?")
                        .bind(0, id)
                        .mapToBean(Order.class).one()
        );
    }

    @Override
    public List<Order> getOrderOfUser(int userId) {
        log.info("Query all orders with userID : " + userId);
        return db.getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM orders WHERE userID = ?")
                        .bind(0, userId)
                        .mapToBean(Order.class).list()
        );
    }

    public List<Order> getOrderOfUserByStatus(int userId, String status) {
        log.info("Query all orders with userID: " + userId+ "with status : " + status);
        return db.getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM orders WHERE userID = ? and shippingStatus = ?")
                        .bind(0, userId)
                        .bind(1, status)
                        .mapToBean(Order.class).list()
        );
    }

    @Override
    public Promotion addPromotion(int id) {
        PromotionDao promotionDao = new PromotionDao();
        return promotionDao.getById(id);
    }

    @Override
    public Payment getMethodPayment(int id) {
        return null;
    }


    // Admin
    // Get 5 recent orders
    @Override
    public List<Order> get5Order(boolean force) {
        log.info("Query 5 recent order with force: " + force);
        var a = db.getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM orders ORDER BY createDate DESC LIMIT ?;")
                        .bind(0, 5)
                        .mapToBean(Order.class).list());

        NumberFormat formatter = NumberFormat.getInstance(Locale.ENGLISH);

        for (Order order : a) {
            try {
                order.setTotal(handle
                        .createQuery("SELECT SUM(p.price * oi.amount) FROM orders AS o JOIN order_item AS oi ON o.id = oi.orderID JOIN products AS p ON p.id = oi.productID WHERE o.id = ?; ")
                        .bind(0, order.getId())
                        .mapTo(Double.class)
                        .first());
                order.setTotalS(formatter.format(order.getTotal()));
            }
            catch (NullPointerException e) {
                order.setTotal(0);
                order.setTotalS(formatter.format(order.getTotal()));
            }
        }

        return a;
    }

    @Override
    public double getTotalOfOrder(int orderId) {
        log.info("Query total with orderId: " + orderId);

        Double totalRevenue = handle
                .createQuery("SELECT SUM(p.price * oi.amount) FROM orders AS o JOIN order_item AS oi ON o.id = oi.orderID JOIN products AS p ON p.id = oi.productID where o.id = ?; ")
                .bind(0, orderId)
                .mapTo(Double.class)
                .first();

        return totalRevenue == null ? 0 : totalRevenue;
    }

    @Override
    public double getTotalRevenue(boolean force) {
        log.info("Query total revenue with force: " + force);

        Double totalRevenue = handle
                .createQuery("SELECT SUM(p.price * oi.amount) FROM orders AS o JOIN order_item AS oi ON o.id = oi.orderID JOIN products AS p ON p.id = oi.productID; ")
                .mapTo(Double.class)
                .first();

        return totalRevenue == null ? 0 : totalRevenue;
    }

    @Override
    public double getTotalProcessing(boolean force) {
        log.info("Query total processing order with force: " + force);

        Double totalProcessing = handle
                .createQuery("SELECT SUM(p.price * oi.amount) FROM orders AS o JOIN order_item AS oi ON o.id = oi.orderID JOIN products AS p ON p.id = oi.productID WHERE o.shippingStatus != 'completed' AND o.shippingStatus != 'cancelled';")
                .mapTo(Double.class)
                .first();

        return totalProcessing == null ? 0 : totalProcessing;
    }

    @Override
    public double getTotalShipped(boolean force) {
        log.info("Query total shipped order with force: " + force);

        Double totalShipped;
        totalShipped = handle
                .createQuery("SELECT SUM(p.price * oi.amount) FROM orders AS o JOIN order_item AS oi ON o.id = oi.orderID JOIN products AS p ON p.id = oi.productID WHERE o.shippingStatus = 'completed';")
                .mapTo(Double.class)
                .first();

        return totalShipped == null ? 0 : totalShipped;
    }

    @Override
    public int getTotalOrderWithStatus(boolean force, ShippingStatus status){
        log.info("Getting total order " + status + " with force: " + force);

        allOrders = getAllOrders(force);
        List<Order> orders = new ArrayList<>();

        for (Order order : allOrders) {
            if (order.getShippingStatus() == status) {
                orders.add(order);
            }
        }

        return orders.size();
    }

    @Override
    public int getTotalOrdersWithPaymentStatus(boolean force, Statuss status){
        log.info("Getting total order " + status + " with force: " + force);

        List<Order> orders = new ArrayList<>();
        var o = handle.createQuery("SELECT o.id FROM orders AS o JOIN payment AS p ON o.paymentID = p.id WHERE p.statuss = ?")
                .bind(0, status)
                .mapToBean(Order.class).list();

        return o.size();
    }

    @Override
    public int updateOrder(int id, String phone, ShippingStatus status) {
        log.info("Updating order...");
        return handle.createUpdate("UPDATE orders SET sdt = ?, shippingStatus = ? WHERE id = ?")
                .bind(0, phone)
                .bind(1, status)
                .bind(2, id)
                .execute();
    }

    @Override
    public int updatePayment(int id, Statuss statuss) {
        log.info("Updating payment of order...");
        return handle.createUpdate("UPDATE payment SET statuss = ? WHERE id = ?")
                .bind(0, statuss)
                .bind(1, id)
                .execute();
    }

    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
        orderDao.log.info("test");

        var li = orderDao.getTotalOrdersWithPaymentStatus(false, Statuss.CANCELLED);

        System.out.println(li);
    }
}
