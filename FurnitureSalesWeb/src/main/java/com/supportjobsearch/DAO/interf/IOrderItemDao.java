package com.supportjobsearch.DAO.interf;

import com.supportjobsearch.Bean.Order;
import com.supportjobsearch.Bean.OrderItem;

import java.util.List;

public interface IOrderItemDao {
    int recordSize();

    OrderItem addOrderItem(OrderItem orderItem);

    void updateOrderItem(int id, int productID, int amount);

    int countAmount(Order order);

    List<OrderItem> getOrderItem(int id);


    OrderItem findByOrderAndProduct(OrderItem orderItem);

}
