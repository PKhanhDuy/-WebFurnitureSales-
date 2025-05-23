package com.supportjobsearch.controller2;

import com.supportjobsearch.Bean.Order;
import com.supportjobsearch.Bean.OrderItem;
import com.supportjobsearch.Bean.OwnAddress;
import com.supportjobsearch.Bean.User;
import com.supportjobsearch.Cart;
import com.supportjobsearch.CartItem;
import com.supportjobsearch.service.OrderItemService;
import com.supportjobsearch.service.OrderService;
import com.supportjobsearch.service.OwnAddressService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Order", value = "/order")
public class OrderController extends HttpServlet {
    OrderItemService orderItemService = new OrderItemService();
    OwnAddressService ownAddressService = new OwnAddressService();
    OrderService service = new OrderService();

    // 9.1.3 Gọi hàm doGet() để xử lý
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // 9.1.4 Lấy cart, user từ session hiện tại và kiểm tra
            HttpSession session = req.getSession(true);
            Cart cart = (Cart) session.getAttribute("cart");
            User user = (User) session.getAttribute("auth");

            if (cart == null) {
                // 9.2.5 Tạo cart mới đưa lên session
                cart = new Cart();
                session.setAttribute("cart", cart);
                // 9.2.6 hiển thị thông báo
                req.setAttribute("error", "Chưa có giỏ hàng");
                req.getRequestDispatcher("/views/web/error.jsp").forward(req, resp);
            }

            if (user != null) {
                int idUser = user.getId();
                // 9.1.5 Tạo đơn hàng mới
                Order order = new Order(idUser);
                this.service.addOrder(order);
                // 9.1.6 Thêm thông tin sản phẩm vào đơn hàng
                processCartItems(cart, order);
                // 9.1.7 Lấy thông tin nhận hàng của user
                List<OwnAddress> address = ownAddressService.getOwnAddress(idUser);
                // 9.1.8 Gắn giá trị cho HttpServletRequest
                req.setAttribute("order", order);
                req.setAttribute("address", address);
                // 9.1.9 Hiển thị trang order
                req.getRequestDispatcher("/order.jsp").forward(req, resp);
            } else {
                // 9.3.5 Chuyển sang trang login
                resp.sendRedirect("/login");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred while processing the order.");
            req.getRequestDispatcher("/views/web/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    // 9.1.6 Thêm thông tin sản phẩm vào đơn hàng
    private void processCartItems(Cart cart, Order order) {
        for (CartItem cartProduct : cart.getListCartItem()) {
            int productId = cartProduct.getId();
            int amount = cartProduct.getQuantity();

            OrderItem orderItem = new OrderItem(order.getId(), productId, amount);
            orderItemService.addOrderItem(orderItem);
        }
    }

}

