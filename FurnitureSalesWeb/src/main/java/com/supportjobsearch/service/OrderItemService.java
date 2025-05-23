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

    // 9.1.6 Thêm thông tin sản phẩm vào đơn hàng
    public OrderItem addOrderItem(OrderItem orderItem) {
        log.info("addOrderItem...");
        return dao.addOrderItem(orderItem);
    }

}
