package com.supportjobsearch.service;

import com.supportjobsearch.Bean.OrderItem;
import com.supportjobsearch.DAO.iml.OrderItemDao;

import java.util.List;

public class OrderItemService extends ServiceBase {
    private OrderItemDao dao = new OrderItemDao();
    private static OrderItemService instance;

    @Override
    public void init() {
        log.info("UserService init...");
        if (dao == null) {
            dao = new OrderItemDao();
        }
    }

    public List<OrderItem> getOrderItem(int id) {
        log.info("getOrderItem...");
        return dao.getOrderItem(id);
    }

    public OrderItem addOrderItem(OrderItem orderItem) {
        log.info("addOrderItem...");
        return dao.addOrderItem(orderItem);
    }

    public void updateOrderItem(int id, int productID, int amount) {
        log.info("update orderitem...");
        this.dao.updateOrderItem(id, productID, amount);
    }

    public OrderItem findOrderItemByProductId(OrderItem orderItem) {
        log.info("find orderitem...");
        return this.dao.findByOrderAndProduct(orderItem);
    }
}
