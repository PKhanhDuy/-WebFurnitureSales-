<%--
  Created by IntelliJ IDEA.
  User: KhanhDuy
  Date: 12/11/2024
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login-register.css">
    <link href="${pageContext.request.contextPath}/public/bootstrap-5.3.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <script src="${pageContext.request.contextPath}/public/bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<div id="container">
    <div class="logo">
        <div class="logobox">
        </div>
    </div>
    <div class="content">

        <div class="box" style="height: 80%">
            <p style="margin-top: -20px; color: red">
                <% String error = (String) request.getAttribute("error");
                    String uname = request.getParameter("uname");
                    if (error == null) error = "";
                    if (uname == null) uname = "";
                %>
                <%= error  %>
            </p>
            <form method="post" action="/login" style="height: 90%">
                <label for="username-l">Tên người dùng hoặc địa chỉ email:</label>
                <input type="text" id="username-l" class="styled-input"
                       style="margin-bottom: 20px; margin-top: 10px; height: 40px"
                       name="uname" value="<%=uname%>" required>
                <label for="password-l">Mật khẩu:</label>
                <input type="password" id="password-l" class="styled-input"
                       style="margin-bottom: 20px; margin-top: 10px; height: 40px"
                       name="pass" required>
                <div class="rememberBox" style="margin-top: -3%">
                    <input type="checkbox" id="remember" name="remember">
                    <label for="remember">Ghi nhớ tôi</label>
                </div>
                <button type="submit" class="loginButton">Đăng nhập</button>
            </form>
        </div>

    </div>
    <div class="footer">
        <div class="subfooter">
            <div class="row">
                <div class="col-md-6 mb-10"><a href="${pageContext.request.contextPath}/views/auth/Forgot-password.jsp">Quên
                    mật
                    khẩu?</a></div>
                <div class="col-md-6 mb-10"><span style="color: black;">Bạn chưa có tài khoản? <a
                        href="${pageContext.request.contextPath}/register"
                        style="color: green !important;">Đăng ký</a></span>
                </div>
                <div class="col-md-6 mb-10"><a href="${pageContext.request.contextPath}/kenes"><i
                        class="bi bi-arrow-left"></i>Đi đến cửa hàng</a></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
