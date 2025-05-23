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

    // 9.1.5 Tạo đơn hàng mới
    public Order addOrder(Order order) {
        log.info("User Service add Order");
        return orderDao.addOrder(order);
    }

}
