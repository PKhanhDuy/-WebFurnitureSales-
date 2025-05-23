<%--
  Created by IntelliJ IDEA.
  User: KhanhDuy
  Date: 12/19/2024
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>Menu Bar</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/admin/style.css">
</head>
<body>
<input type="checkbox" id="sidebar-toggle">
<div class="sidebar">
    <div class="sidebar-header">
        <h3 class="brand">
            <span class="ti-panel"></span>
            <span>Dashboard</span>
        </h3>
        <label for="sidebar-toggle" class="ti-menu-alt"></label>
    </div>

    <div class="sidebar-menu">
        <ul>
            <!-- For management -->
            <li>
                <a class="separator">
                    General
                </a>
            </li>

            <li>
                <a href="/admin/dashboard">
                    <span class="ti-home"></span>
                    <span>Trang chủ</span>
                </a>
            </li>
            <c:if test="${role eq 'Supreme'}">
                <li>
                    <a href="/admin/ad-mana">
                        <span class="ti-wheelchair"></span>
                        <span>Admins</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${role eq 'Supreme' or role eq 'User Management'}">
                <li>
                    <a href="/admin/users">
                        <span class="ti-user"></span>
                        <span>Người dùng</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${role eq 'Supreme'}">
                <li>
                    <a href="/admin/permit">
                        <span class="ti-agenda"></span>
                        <span>Vai trò & Quyền hạn</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${role eq 'Order Management' or role eq 'Supreme' or role eq 'User Management' or role eq 'Product Management'}">
                <li>
                    <a href="/admin/products">
                        <span class="ti-truck"></span>
                        <span>Quản lý sản phẩm</span>
                    </a>
                </li>
                <li>
                    <a href="/admin/category">
                        <span class="ti-menu"></span>
                        <span>Quản lý danh mục</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${role eq 'Supreme' or role eq 'User Management'}">
                <li>
                    <a href="/admin/inv">
                        <span class="ti-server"></span>
                        <span>Quản lý hàng tồn</span>
                    </a>
                </li>
                <li>
                    <a href="/admin/discount">
                        <span class="ti-ticket"></span>
                        <span>Giảm giá</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${role eq 'Order Management' or role eq 'Supreme' or role eq 'User Management' or role eq 'Product Management'}">
                <li>
                    <a href="/admin/orders">
                        <span class="ti-receipt"></span>
                        <span>Quản lý đơn</span>
                    </a>
                </li>
                <li>
                    <a href="/admin/payment">
                        <span class="ti-money"></span>
                        <span>Thanh toán</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${role eq 'Supreme' or role eq 'User Management' or role eq 'Reporting'}">
                <li>
                    <a href="/admin/report">
                        <span class="ti-bar-chart"></span>
                        <span>Báo cáo doanh thu</span>
                    </a>
                </li>
            </c:if>
            <!-- For user self -->

            <li>
                <a class="separator">Users</a>
            </li>

            <li>
                <a href="/admin/profile">
                    <span class="ti-user"></span>
                    <span>Profile</span>
                </a>
            </li>

            <!-- For support -->

            <li>
                <a class="separator">
                    Support
                </a>
            </li>

            <li>
                <a href="/views/admin/privacy-policy.jsp">
                    <span class="ti-info-alt"></span>
                    <span>Privacy policy</span>
                </a>
            </li>
            <li>
                <a href="/views/admin/faqs.jsp">
                    <span class="ti-help-alt"></span>
                    <span>FAQs</span>
                </a>
            </li>
            <li>
                <a href="/views/admin/help-center.jsp">
                    <span class="ti-headphone-alt"></span>
                    <span>Help center</span>
                </a>
            </li>
            <li>
                <a href="/logout">
                    <span class="ti-shift-left"></span>
                    <span>Log out</span>
                </a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
