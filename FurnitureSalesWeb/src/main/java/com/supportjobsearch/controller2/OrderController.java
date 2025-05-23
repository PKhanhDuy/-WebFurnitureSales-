package com.supportjobsearch.controller2;

import com.supportjobsearch.Bean.Order;
import com.supportjobsearch.Bean.OrderItem;
import com.supportjobsearch.Bean.OwnAddress;
import com.supportjobsearch.Cart;
import com.supportjobsearch.CartItem;
import com.supportjobsearch.Location.LocationData;
import com.supportjobsearch.service.OrderItemService;
import com.supportjobsearch.service.OrderService;
import com.supportjobsearch.service.OwnAddressService;
import com.supportjobsearch.User;
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

    // 9.5.1 hệ thống hiển thị ra trang order
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            HttpSession session = req.getSession(true);
            User user = (User) session.getAttribute("auth");

            if (user != null) {
                int idUser = user.getId();
                List<OrderItem> orderitems = orderItemService.getOrderItem(idUser);
                List<OwnAddress> address = ownAddressService.getOwnAddress(idUser);

                double totalMoney = service.getTotalRevenue(true);

                req.setAttribute("orderitems", orderitems);
                req.setAttribute("total", totalMoney);
                req.setAttribute("address", address);

                req.getRequestDispatcher("/views/web/order/order.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("/login");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred while processing the order.");
            req.getRequestDispatcher("/views/web/error.jsp").forward(req, resp);
        }
    }

    // 9.0.1 người dùng chọn nút mua hàng từ trang Cart
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            // 9.1.1 hệ thống lấy value cart và user từ session hiện tại
            HttpSession session = req.getSession(true);
            Cart cart = (Cart) session.getAttribute("cart");
            User user = (User) session.getAttribute("auth");

            // 9.1.2 hệ thống kiểm tra cart có tồn tại không
            if (cart == null) {
                // 9.1.2.1 nếu chưa có, hệ thống tạo cart mới và thông báo
                cart = new Cart();
                session.setAttribute("cart", cart);
                req.setAttribute("error", "Chưa có giỏ hàng");
                req.getRequestDispatcher("/views/web/error.jsp").forward(req, resp);
            }

            // 9.1.3 hệ thống kiếm tra user có tồn tại không
            if (user != null) {
                // 9.2.1 hệ thống khởi tạo đơn hàng mới cho user
                int idUser = user.getId();
                Order order = new Order(idUser);
                this.service.addOrder(order);
                // 9.3.1 hệ thống thêm thông tin sản phẩm vào đơn hàng
                processCartItems(cart, order);
                // 9.4.1 hệ thống lấy thông tin nhận hàng
                handleShippingInfo(req, resp, idUser);
                // 9.5.1 hệ thống hiển thị ra trang order
                resp.sendRedirect(req.getContextPath() + "/order");
            } else {
                // 9.1.3.1 Nếu uer không hợp lệ thì hệ thống sẽ chuyển sang trang login
                resp.sendRedirect("/login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 9.3.1 hệ thống thêm thông tin sản phẩm vào đơn hàng (sử dụng vòng lặp for thêm từng sản phẩm)
    private void processCartItems(Cart cart, Order order) {
        for (CartItem cartProduct : cart.getListCartItem()) {
            int productId = cartProduct.getId();
            int amount = cartProduct.getQuantity();

            OrderItem orderItem = orderItemService.findOrderItemByProductId(new OrderItem(order.getId(), productId));

            if (orderItem != null) {

                orderItem.setAmount(orderItem.getAmount() + amount);
                orderItemService.updateOrderItem(orderItem.getId(), orderItem.getProductID(), orderItem.getAmount());
            } else {
                orderItem = new OrderItem(order.getId(), productId, amount);
                orderItemService.addOrderItem(orderItem);
            }
        }
    }

    // 9.4.1 hệ thống lấy thông tin nhận hàng
    private void handleShippingInfo(HttpServletRequest req, HttpServletResponse resp, int idUser) throws IOException {

        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String idCity = req.getParameter("city");
        String city = LocationData.findProvine(idCity);
        String district = req.getParameter("district");
        // 9.4.2 hệ thống kiểm tra thông tin nhận hàng
        if (isShippingInfoValid(name, phone, address, city, district)) {
            // 9.4.3 hệ thống cập nhật thông tin nhận hàng vào order
            this.ownAddressService.updateOwnAddress(name, phone, city, address, idUser, idUser);
        } else {
            // 9.4.2.1 nếu nhập thiếu thông tin, hệ thống trả về thông báo lỗi
            resp.getWriter().write("{\"success\": false, \"error\": \"Vui lòng điền đầy đủ thông tin.\"}");
        }
    }

    private boolean isShippingInfoValid(String name, String phone, String address, String city, String district) {
        return name != null && !name.isEmpty() &&
                phone != null && !phone.isEmpty() &&
                address != null && !address.isEmpty() &&
                city != null && !city.isEmpty() &&
                district != null && !district.isEmpty();
    }


}

