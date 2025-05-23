package com.supportjobsearch.DAO.interf;

import com.supportjobsearch.Bean.Order;
import com.supportjobsearch.Bean.Payment;
import com.supportjobsearch.Bean.Promotion;
import com.supportjobsearch.enums.ShippingStatus;
import com.supportjobsearch.enums.Statuss;
import org.jdbi.v3.core.inlined.org.antlr.v4.runtime.atn.$SemanticContext;

import java.util.List;

public interface IOrderDao {
    int recordSize();

    List<Order> getAllOrders(boolean force);

    public Order addOrder(Order order);

    Order updateOrderByID(int id, int orderID, int productID, int amount);

    public boolean deleteOrder(int id);

    //    public ShippingStatus getShippingStatus(int id);
    public Order getOrderById(int id);

    public List<Order> getOrderOfUser(int userId);

    public Promotion addPromotion(int id);

    public Payment getMethodPayment(int id);

    public List<Order> get5Order(boolean force);

    public double getTotalOfOrder(int orderId);
    public double getTotalRevenue(boolean force);

    public double getTotalProcessing(boolean force);

    public double getTotalShipped(boolean force);

    public int getTotalOrderWithStatus(boolean force, ShippingStatus status);

    public int getTotalOrdersWithPaymentStatus(boolean force, Statuss status);

    int updateOrder(int id, String phone, ShippingStatus status);
    int updatePayment(int id, Statuss statuss);
}
