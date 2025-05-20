<%@ page import="com.supportjobsearch.Cart" %><%--
  Created by IntelliJ IDEA.
  User: KhanhDuy
  Date: 12/14/2024
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/cart.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/bootstrap-5.3.3-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/public/js/curtainmenu.js"></script>
    <script src="${pageContext.request.contextPath}/public/js/CartJS.js"></script>
    <script src="${pageContext.request.contextPath}/public/js/Promotion.js"></script>
    <style>
        /* Kiểu thông báo */
        .notification {
            position: fixed;
            top: 140px;
            right: 20px;
            padding: 10px 20px;
            background-color: #4caf50; /* Màu xanh lá biểu thị thành công */
            color: white;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            font-size: 14px;
            z-index: 1000;
            opacity: 1;
            transition: opacity 0.5s ease, transform 0.5s ease;
        }

        /* Ẩn thông báo */
        .hidden {
            opacity: 0;
            transform: translateY(-20px);
            pointer-events: none;
        }

        .remove-item {
            border: none;
            background-color: white;
        }

        .red {
            background-color: red;
        }

        .green {
            color: darkgreen;
        }

        .scroll-cart {
            max-height: 65%;
            height: 65% !important;
            overflow-y: auto;
            overflow-x: hidden;
            padding-right: 10px;
        }

        .cart-actions {
            position: sticky; /* Giữ cố định trong container */
            height: 150px !important;
            bottom: 0; /* Đặt vị trí tại đáy của container */
            background-color: #fff; /* Nền trắng để nổi bật */
            padding: 10px;
            z-index: 10; /* Đảm bảo không bị che bởi phần khác */
        }

        .watch-cart,
        .check-out {
            padding: 5px 20px;
            margin-bottom: 10px;
        }

        #input-quantity {
            width: 55px;
        }
    </style>
</head>
<body>
<!-- Start Header -->
<div class="container-hd">
    <header>
        <div class="header-top-hd">
            <div class="language-switcher-hd">
                <a href="#">EN</a> | <a href="#"><Strong>VN</Strong></a>
            </div>
            <div class="contact-info-hd">
                <div class="phone-hd">
                    <a class="fas fa-regular fa-phone"></a>
                </div>

                <a href=".${pageContext.request.contextPath}/contact.jsp">0906 904 114</a>
                <div class="about-hd">
                    <a href="${pageContext.request.contextPath}/views/web/about.jsp">Giới thiệu</a>
                    <a href="#">Khuyến mãi </a>
                </div>
            </div>
            <div class="header-icons-hd">
                <a href="#" class="fas fa-map-marker-alt"></a>
                <a href="#" class="fas fa-heart"></a>
                <a href="#" class="fas fa-shopping-cart" onclick="showCart()"></a>
                <a href="#" class="fas fa-light fa-user"></a>
                <h4 style="font-weight: lighter; margin-left: -15px; font-size: large; margin-top: 10px;">
                    <c:if test="${sessionScope.auth != null}">
                        <a href="${pageContext.request.contextPath}/profile">
                                ${sessionScope.auth.username}
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.auth == null}">
                        <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                    </c:if>
                </h4>
            </div>
        </div>
        <!-- create mobile menu -->
        <div id="background-trans" hidden class="mfp-bg mfp-ready"></div>
        <div class="header-bottom-hd">
            <div class="logo-hd">
                <a href="${pageContext.request.contextPath}/kenes"><img src="${pageContext.request.contextPath}/public/images/logos/logo3.png" alt="Logo">
                </a>
            </div>
            <nav class="main-nav">
                <a style="color: black; border: none;" class="btn dropdown-toggle"
                   href="${pageContext.request.contextPath}/list-product">SẢN PHẨM</a>
                <ul class="dropdown-menu">
                    <div class="row" id="row-873750177">
                        <c:forEach var="cates" items="${mapCate}">
                            <div class="col medium-2 small-6 large-2">
                                <div class="col-inner">
                                    <div class="ux-menu stack stack-col justify-start">
                                            <%--cates.value là danh sách các danh mục trong map--%>
                                        <c:forEach var="c" items="${cates.value}">
                                            <div class="ux-menu-link flex menu-item">
                                                <a class="ux-menu-link__link flex" href="products?cateID=${c.id}">
                                                <span class="ux-menu-link__text">
                                                        ${c.cateName} </span>
                                                </a>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </ul>
                <a style="margin-top: 5px;" href="#">PHÒNG</a>
                <a style="margin-top: 5px;" href="#">BỘ SƯU TẬP</a>
            </nav>
            <div class="search-bar-hd">
                <input type="text" placeholder="Tìm sản phẩm">
                <button><i class="fa-solid fa-magnifying-glass"></i></button>
            </div>
        </div>
    </header>
</div>
<!-- end Header -->
<div id="mask-container">
    <div id="mask-cart">
    </div>
    <div id="content-cart">
        <div class="exit-box center-items">
            <h5>Giỏ hàng</h5>
            <i class="bi bi-x-square" id="close-pop-up"></i>
            <div class="block"></div>
        </div>

        <div id="list-product-cart" class="scroll-cart">
            <c:forEach items="${sessionScope.cart.list}" var="cp">
                <div class="row">
                    <div class="col-md-12 col-12 order">
                        <div class="image center-items">
                            <img src="${cp.img}" alt="">
                        </div>
                        <div class="detail-order center-items" style="justify-content: left;">
                            <div>
                                <h6>${cp.name}</h6>
                                <span>${cp.quantity}</span> x <span><f:formatNumber type="currency" currencySymbol="đ"
                                                                                    value="${cp.price}"/></span>
                            </div>
                        </div>
                        <div class="close-orders center-items">
                            <button class="remove-item" data-id="${cp.id}">
                                <i class="bi bi-x-circle"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div id="pay-pal" class="cart-actions">
            <div class="total-price">
                <div class="money-text">
                    <p>Thành tiền: </p>
                </div>
                <div class="money-number">
                    <p class="total-cart"><f:formatNumber type="currency" currencySymbol="đ"
                                                          value="${sessionScope.cart.total}"/></p>
                </div>

            </div>
            <div class="watch-cart center-items">
                <a href="${pageContext.request.contextPath}/CartController" onclick="hiddenCart()">XEM GIỎ HÀNG</a>
            </div>
            <div class="check-out center-items">
                <a href="">THANH TOÁN</a>
            </div>

        </div>
    </div>
</div>

<!-- Phần giao diện chính của giỏ hàng -->
<div id="container">
    <div id="notification" class="notification hidden">Sản phẩm đã được thêm vào giỏ hàng!</div>
    <div id="login-notification" class="notification hidden red">Bạn chưa đăng nhập</div>
    <div id="cart-title">
        <h1>Giỏ hàng
            <c:if test="${sessionScope.cart != null}">
                <span class="total-count">${sessionScope.cart.list.size()}</span>
            </c:if>
            <c:if test="${sessionScope.cart == null}">
                <span class="total-count">0</span>
            </c:if>
        </h1>
    </div>
    <div id="content">
        <div class="row">
            <div id="products-of-cart" class="col-sm-7 col-md-7 product-detail">
                <c:forEach items="${sessionScope.cart.list}" var="cp">
                    <div class="cart-item">
                        <div class="image-box">
                            <img src="${cp.img}" alt="Armchair Mây mới"
                                 class="item-image">
                        </div>
                        <div class="item-details">
                            <h3>${cp.name}</h3>
                            <p class="item-price"><f:formatNumber type="currency" currencySymbol="đ"
                                                                  value="${cp.price}"/></p>
                            <p class="wishlist"><span><i class="bi bi-heart"></i></span> Thêm vào Wishlist</p>
                        </div>
                        <div class="item-actions item-product" data-id="${cp.id}" data-name="${cp.name}"
                             data-img="${cp.img}" data-price="${cp.price}">
                            <button class="remove-item" data-id="${cp.id}"
                                    style="border: none; background-color: white;">
                                <i class="bi bi-x-circle"></i>
                            </button>
                            <div class="quantity buttons-added left-btn">

                                <input type="button" value="-" class="minus-btn" id="button-minus-quantity">
                                <input type="number" name="quatity" class="quantity-input" id="input-quantity"
                                       value="${cp.quantity}" min="1"

                                       inputmode="numeric" autocomplete="off">
                                <input type="button" value="+" class="plus-btn" id="button-plus-quantity">
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="col-sm-5 col-md-5 order-detail">
                <div class="cart-total">
                    <h2>Tổng đơn hàng</h2>
                    <table class="shop-total">
                        <tbody>
                        <tr class="cart-subtotal">
                            <th>Thành tiền</th>
                            <td data-title="Thành tiền" class="t-bold" id="old-total">
                                <c:if test="${sessionScope.auth == null}">
                                    <f:formatNumber type="currency" currencySymbol="đ" value="0.0"/>
                                </c:if>
                                <c:if test="${sessionScope.auth != null}">
                                    <p class="total-cart">
                                        <f:formatNumber type="currency" currencySymbol="đ"
                                                        value="${sessionScope.cart.total}"/>
                                    </p>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="row sale-buy" style="row-gap: 5px">
                        <div class="col-sm-6 col-md-6 sale-input">
                            <input type="text" placeholder="Mã giảm giá" value="" id="promotion-code">
                        </div>
                        <div class="col-sm-6 col-md-6">
                            <div class="use-button fake-btn">
                                <button id="submit-promotion-btn" type="submit"
                                        style="background-color: black; color: white; border:none; padding: 5px 0">
                                    SỬ
                                    DỤNG
                                </button>
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-12" id="notification-promotion"></div>
                        <div class="col-sm-6 col-md-6 text-total">
                            <p>Tổng cộng</p>
                        </div>
                        <div class="col-sm-6 col-md-6 t-bold total-price">
                            <p id="total-after-promotion" class="total-cart">
                                <c:if test="${sessionScope.auth == null}">
                                    <f:formatNumber type="currency" currencySymbol="đ" value="0.0"/>
                                </c:if>
                                <c:if test="${sessionScope.auth != null}">
                                    <f:formatNumber type="currency" currencySymbol="đ"
                                                    value="${sessionScope.cart.total}"/>
                                </c:if>
                            </p>
                        </div>
                    </div>
                    <div class="shipping-infor">
                        <h5>Thông tin giao hàng</h5>
                        <p>Đối với những sản phẩm có sẵn tại khu vực, chúng tôi sẽ giao hàng
                            trong vòng 2-7 ngày tới. Đối với những sản phẩm không có sẵn, thời gian
                            giao hàng sẽ được nhân viên thông báo đến quý khách.
                        </p>
                        <p>Từ 2-6: 8:80 - 17:30</p>
                        <p>Thứ 7, CN: 9:30 - 16:30</p>
                        <a href="#" style="text-decoration: none; color: #fbaf1a;">Của hàng gần bạn</a>
                    </div>
                    <div class="btn-group">
                        <div class="row">
                            <div class="col-sm-6 col-md-6">
                                <div class="buy-button use-button fake-btn">
                                    <a href="${pageContext.request.contextPath}/list-product"
                                       style="color: white; text-decoration: none;">
                                        <p>Tiếp tục mua hàng</p>
                                    </a>

                                </div>
                            </div>
                            <div class="col-sm-6 col-md-6">
                                <div class="use-button fake-btn">
                                    <a href="${pageContext.request.contextPath}/order"
                                       style="color: white; text-decoration: none;">
                                        <p>Đặt hàng</p>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer -->
<!-- <iframe src="../../common/footer.jsp" frameborder="0" id="footer"></iframe>      -->
<footer class="footer">
    <div class="footer-container">
        <!-- Left Column -->
        <div class="footer-column">
            <h3>KẾT NỐI VỚI KANE'S</h3>
            <img src="${pageContext.request.contextPath}/public/images/logos/logo3.png" alt=" Logo" class="footer-logo">
            <p>FOLLOW US</p>
            <p>Instagram – Youtube – Facebook</p>
            <button class="footer-button">HỆ THỐNG CỬA HÀNG</button>
        </div>

        <!-- Second Column -->
        <div class="footer-column">
            <h3 style="justify-content: center;">KANE'S</h3>
            <ul>
                <li><a href="#">Giới thiệu</a></li>
                <li><a href="#">Chuyện KANE'S</a></li>
                <li><a href="#">Tổng công ty</a></li>
                <li><a href="#">Tuyển dụng</a></li>
                <li><a href="#">Thẻ hội viên</a></li>
                <li><a href="#">Đổi trả hàng</a></li>
            </ul>
        </div>

        <!-- Third Column -->
        <div class="footer-column">
            <h3>CẢM HỨNG</h3>
            <ul>
                <li><a href="#">Sản phẩm</a></li>
                <li><a href="#">Ý tưởng và cảm hứng</a></li>
            </ul>
        </div>

        <!-- Fourth Column -->
        <div class="footer-column">
            <h3>NEWSLETTER</h3>
            <p>Hãy để lại email của bạn để nhận được những ý tưởng trang trí mới và thông tin ưu đãi từ KANE'S</p>
            <p>Email: kanescare@akacompany.com.vn</p>
            <p>Hotline: 18007200</p>
            <div class="newsletter">
                <input type="email" placeholder="Nhập email của bạn">
                <button class="subscribe-button">ĐĂNG KÝ</button>
            </div>
        </div>
    </div>

</footer>

<script>

    //    const items = document.querySelectorAll(".cart-item");
    //    items.forEach((product) =>{
    //        const plusBtn = product.querySelector(".plus-btn")
    //        const minusBtn = product.querySelector(".minus-btn")
    //        const quantityInput = product.querySelector(".quantity-input")
    //
    //        minusBtn.addEventListener('click', () => {
    //            let currentValue = parseInt(quantityInput.value)
    //            if (currentValue > 1) {
    //                quantityInput.value = currentValue - 1;
    //            }
    //        });
    //
    //        // Xử lý khi nhấn nút "+"
    //        plusBtn.addEventListener('click', () => {
    //            let currentValue = parseInt(quantityInput.value);
    //            quantityInput.value = currentValue + 1;
    //        });
    //    })


    // Xử lí phần ẩn hiện giỏ hàng
    const pop_up_cart = document.getElementById('mask-container')

    function showCart() {
        document.body.style.overflow = 'hidden';
        pop_up_cart.style.opacity = 1;
        pop_up_cart.style.zIndex = 2000;
        pop_up_cart.style.transform = 'translateX(0)'
        pop_up_cart.style.transition = 'transform 0.5s ease'
    }

    //ẩn đi phần giỏ hàng
    function hiddenCart() {
        document.body.style.overflow = 'auto';
        pop_up_cart.style.transform = 'translateX(50%)'
        pop_up_cart.style.transition = 'transform 0.5s ease'
        pop_up_cart.style.opacity = 0;
        pop_up_cart.style.zIndex = -2000;
    }

    document.getElementById('mask-cart').addEventListener('click', hiddenCart)
    document.getElementById('close-pop-up').addEventListener('click', hiddenCart)

</script>

</body>
</html>
