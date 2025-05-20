package com.supportjobsearch.service;

import com.supportjobsearch.Bean.Order;
import com.supportjobsearch.enums.ShippingStatus;
import com.supportjobsearch.enums.Statuss;
import com.supportjobsearch.DAO.iml.OrderDao;

import java.util.ArrayList;
import java.util.List;

public class OrderService extends ServiceBase {

    OrderDao orderDao = new OrderDao();

    private static OrderService instance;

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    @Override
    public void init() {
        log.info("UserService init...");
        if (orderDao == null) {
            orderDao = new OrderDao();
        }
    }

    public OrderService() {
        super();
    }

    // Get 5 recent orders
    // @param : forceUpdate -> force update query
    public List<Order> get5RecentOrders(boolean forceUpdate) {
        log.info("UserService get5RecentOrders...");
        return orderDao.get5Order(forceUpdate);
    }

    public Order addOrder(Order order) {
        log.info("User Service add Order");
        return orderDao.addOrder(order);
    }

    public List<Order> getAllOrder(boolean forceUpdate) {
        log.info("User Service getAllOrder...");
        return orderDao.getAllOrders(forceUpdate);
    }

    public double getTotalOfOrder(int userId){
        log.info("User Service getTotalOfOrder...");
        return orderDao.getTotalOfOrder(userId);
    }


    // Get total revenue
    // @param : forceUpdate -> force update query
    public double getTotalRevenue(boolean forceUpdate) {
        log.info("UserService getTotalRevenue...");
        return orderDao.getTotalRevenue(forceUpdate);
    }

    // Get total processing money
    // @param : forceUpdate -> force update query
    public double getTotalProcessing(boolean forceUpdate) {
        log.info("UserService getTotalProcessing...");
        return orderDao.getTotalProcessing(forceUpdate);
    }

    // Get total shipped order's money
    // @param : forceUpdate -> force update query
    public double getTotalShipped(boolean forceUpdate) {
        log.info("UserService getTotalShipped...");
        return orderDao.getTotalShipped(forceUpdate);
    }

    public int getTotalShippedOrder(boolean forceUpdate) {
        log.info("UserService getTotalShippedOrder...");
        return orderDao.getTotalOrderWithStatus(forceUpdate, ShippingStatus.Completed);
    }

    public int getTotalOrderWithStatus(boolean forceUpdate, ShippingStatus status) {
        log.info("UserService getTotalOrderWithStatus...");
        return orderDao.getTotalOrderWithStatus(forceUpdate, status);
    }

    public int getTotalOnProgressOrder(boolean forceUpdate) {
        log.info("UserService getTotalDeliveringOrder...");
        return orderDao.getTotalOrderWithStatus(forceUpdate, ShippingStatus.Delivering) + orderDao.getTotalOrderWithStatus(forceUpdate, ShippingStatus.Packaging);
    }

    public int getTotalCancelledOrder(boolean forceUpdate) {
        log.info("UserService getTotalCancelledOrder...");
        return orderDao.getTotalOrderWithStatus(forceUpdate, ShippingStatus.Cancelled);
    }

    public int getTotalPendingOrder(boolean forceUpdate) {
        return orderDao.getTotalOrdersWithPaymentStatus(forceUpdate, Statuss.PENDING);
    }

    public int getTotalRefundedOrder(boolean forceUpdate) {
        return orderDao.getTotalOrdersWithPaymentStatus(forceUpdate, Statuss.CANCELLED);
    }



    public Order getOrderById(int id) {
        Order o = null;
        try {
            o = orderDao.getOrderById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return o;
    }
    public List<Order> getOrderOfUser(int userId) {
        List<Order> os = null;
        try {
            os = orderDao.getOrderOfUser(userId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return os;
    }

    public List<Order> getOrderOfUserByStatus(int userId, String status) {
        List<Order> os = null;
        try {
            os = orderDao.getOrderOfUserByStatus(userId, status);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return os;
    }

    public boolean updateOrder(int id, int pId, String phone, ShippingStatus status, Statuss statuss) {
        log.info("UserService updateOrder...");

        if (status == ShippingStatus.Completed && statuss == Statuss.CANCELLED) {
            return false;
        }
        if (status == ShippingStatus.Completed && statuss == Statuss.PENDING) {
            return false;
        }
        if (status == ShippingStatus.Cancelled || statuss == Statuss.CANCELLED) {
            statuss = Statuss.CANCELLED;
            status = ShippingStatus.Cancelled;
        }

        int a = orderDao.updateOrder(id, phone, status);
        int b = orderDao.updatePayment(pId, statuss);

        return (a > 0) && (b > 0);
    }

}
