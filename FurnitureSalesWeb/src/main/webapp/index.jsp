<%--
  Created by IntelliJ IDEA.
  User: dttan
  Date: 12/18/2024
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/product.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/public/js/Cart.js"></script>

</head>
<body>
<div id="mask-container">
    <div id="mask-cart">
    </div>
    <div id="content-cart">
        <div class="exit-box center-items">
            <h5>Giỏ hàng</h5>
            <i class="bi bi-x-square" id="close-pop-up"></i>
            <div class="block"></div>
        </div>
        <c:forEach items="${sessionScope.cart.list}" var="cp">
            <div class="list-product-cart">
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
            </div>
        </c:forEach>
        <div id="pay-pal">
            <div class="total-price">
                <div class="money-text">
                    <p>Thành tiền: </p>
                </div>
                <div class="money-number">
                    <p><f:formatNumber type="currency" currencySymbol="đ"
                                       value="${sessionScope.cart.total}"/></p>
                </div>
            </div>
            <div class="watch-cart center-items">
                <a href="${pageContext.request.contextPath}/CartController">XEM GIỎ HÀNG</a>
            </div>
            <div class="check-out center-items">
                <a href="${pageContext.request.contextPath}/order">THANH TOÁN</a>
            </div>

        </div>
    </div>
</div>


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

                <a href="${pageContext.request.contextPath}/views/web/contact.jsp">0906 904 114</a>
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
                <a href="${pageContext.request.contextPath}/kenes"> <img
                        src="${pageContext.request.contextPath}/public/images/logos/logo3.png"
                        alt="Logo"></a>
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
            <%-- 6.1.1 Người dùng truy cập trang chủ (index.jsp) --%>
            <%-- 6.1.2 Người dùng nhập từ khóa tìm kiếm vào ô tìm kiếm ở trang chủ --%>
            <form action="/search" method="get">
                <div class="search-bar-hd">
                    <input id="search-input" name="search-input" type="text" placeholder="Tìm sản phẩm">
                    <%-- 6.1.3 Người dùng nhấn nút Tìm kiếm (icon kính lúp), form sẽ submit GET request đến
                    SearchController với tham số search-input --%>
                    <button type="submit">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </div>
            </form>
            <script>
                function getValue() {
                    const searchInput = document.getElementById("search-input");
                    const inputValue = searchInput.value;
                    console.log("Giá trị tìm kiếm:", inputValue);
                }
            </script>
        </div>
    </header>
</div>
<!-- Nội dung trang chủ -->
<div class="body">
    <div class="homepage-button-pic">        <img class="homepage-pic" src="${pageContext.request.contextPath}/public/images/banners/homepage.jpg" alt=""/>
        <div class="text-home">
            <h1 style="font-size: 64px; position: relative;">BỘ SƯU TẬP MÂY</h1>
            <p style="font-size: x-large; position: relative;"><strong>Nét đẹp đương đại</strong></p>
            <a href="#" class="button-explore">
                <button class="explore-button">Khám phá ngay!</button>
            </a>
        </div>
    </div>
    <div class="img-banner">
        <div class="left-img">
            <img style="width: 750px;height: 720px;"
                 src="${pageContext.request.contextPath}/public/images/banners/BST-COASTAL-SOFA-VANG-2.jpg"
                 alt="">
        </div>
        <div class="right-img">
            <div>
                <img src="${pageContext.request.contextPath}/public/images/banners/giuong-ngu-pio-1.jpg" alt="">
            </div>
            <div>
                <img src="${pageContext.request.contextPath}/public/images/banners/banner-armchair-nhaxinh-31-1-24.jpg"
                     alt="">
            </div>
            <div>
                <img src="${pageContext.request.contextPath}/public/images/banners/nha-xinh-banner-ban-an-vuong-24423.jpg"
                     alt="">
            </div>
            <div>
                <img src="${pageContext.request.contextPath}/public/images/banners/nha-xinh-ghe-an-phong-an-749x800.jpg"
                     alt="">
            </div>

        </div>
    </div>
    <div class="search-home">
        <input type="search" id="woocommerce-product-search-field-2" class="search-field mb-0"
               placeholder="Tìm sản phẩm"
               value="" name="s" autocomplete="off">
        <button class="but-search" style="background-color: #d26e4b;"><i
                class="fa-solid fa-magnifying-glass"></i></button>
    </div>
    <div class="body-imga">
        <section class="part1">
            <div class="left-side-part1">
                <img data-aos="zoom-in" style="width: 100%;height: auto;"
                     src="${pageContext.request.contextPath}/public/images/banners/nha-xinh-banner-phong-khach-31-1-24.jpg"
                     alt="">
            </div>
            <div class="middle-text">
                <div class="left-text">
                    <div class="top-text">
                        <h2>Không gian phòng khách</h2>
                        <p>Phòng khách là không gian chính của ngôi nhà, là nơi sum họp gia đình</p>
                        <h5 style="font-weight: lighter;" href="#">MẪU THIẾT KẾ <i class="fa-solid fa-arrow-right"
                                                                                   style="color: #343434;"></i></h5>
                    </div>
                    <div class="bot-text">
                        <h2>Đồ trang trí</h2>
                        <p>Mang lại những nguồn cảm hứng và nét sinh động cho không gian</p>
                        <h5 style="font-weight: lighter;" href="#">KHÁM PHÁ <i class="fa-solid fa-arrow-right"
                                                                               style="color: #343434;"></i></h5>
                    </div>
                </div>
            </div>
            <div class="right-side-part1">
                <img data-aos="zoom-in" style="margin-right: 20px; width: 100%;"
                     src="${pageContext.request.contextPath}/public/images/banners/binhhoa.jpg" alt="">
            </div>
        </section>
        <section class="part2">
            <div class="left-part2">
                <h2>NỘI THẤT TINH TẾ</h2>
                <p>Với kinh nghiệm hơn 25 năm trong hoàn thiện nội thất, Nhà Xinh mang đến giải pháp toàn diện trong bao
                    gồm
                    thiết kế, trang trí và cung cấp nội thất trọn gói. Sở hữu đội ngũ chuyên nghiệp và hệ thống 10 cửa
                    hàng, Nhà
                    Xinh là lựa chọn cho không gian tinh tế và hiện đại.</p>
                <button class="xemthem" style="font-size: large;font-weight: bold;">Xem thêm</button>
            </div>
            <div class="right-part2">
                <img style="width: 100%;"
                     src="${pageContext.request.contextPath}/public/images/banners/nha-xinh-banner-ban-an-vuong-24423.jpg"
                     alt="">
            </div>
        </section>
        <section class="new-product-header">
            <h3 href="#">Sản phẩm mới</h3>
            <a href="${pageContext.request.contextPath}/list-product" style="width: 100px;">Xem tất cả</a>
        </section>
        <section style="margin-left: 5%; margin-right: 5%; padding-top: 50px;">
            <div class="card-group">
                <c:forEach var="product" items="${list_product}">
                    <div class="card">
                        <div class="card-d">
                            <img src="${pageContext.request.contextPath}/${product.thumb}"
                                 class="card-img-top" alt="${product.proName}">
                            <img class="back"
                                 src="${pageContext.request.contextPath}/${product.thumb}"
                                 alt="Back Image">
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">${product.proName}</h5>
                            <p class="card-text"><i class="fa-regular fa-heart"></i></br><fmt:formatNumber
                                    value="${product.price}" type="currency" currencySymbol="đ"/>
                            </p>
                        </div>
                        <div class="card-footer product" style="background: white" data-id="${product.id}"
                             data-name="${product.proName}"
                             data-img="${product.thumb}" data-price="${product.price}">
                            <button style="width: 120px;margin-right:20px;height: 50px; " class="add-to-cart">Thêm vào
                                giỏ
                            </button>
<%--                            <button style="text-decoration:none ;color:white;width: 100px;height: 50px;background: black;"--%>
<%--                                    class="see-more">--%>
<%--                                <a  href="javascript:void(0);"--%>
<%--                                   onclick="showProductDetails(${product.id}, ${product.atributeID}, ${product.cateID})">--%>
<%--                                    <p>XEM THÊM</p></a></button>--%>
                            <button style="text-decoration:none ;color:white;width: 100px;height: 50px;background: black;"
                                    class="see-more"
                                    onclick="showProductDetails(${product.id}, ${product.atributeID}, ${product.cateID})">
                                XEM THÊM
                            </button>

                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>

        <section class="part3">
            <div class="content-wrapper">
                <div class="support-form">
                    <div class="hotline">
                        <p class="fa-solid fa-phone" style="color: #000000;"> HOTLINE: 1800 7200</p>
                    </div>
                    <h2>Bạn cần hỗ trợ?</h2>
                    <p>Xin vui lòng để lại yêu cầu hỗ trợ của bạn.</p>

                    <form class="sp">
                        <div class="form-row">
                            <input type="text" placeholder="Họ tên" required>
                            <input type="tel" placeholder="+(84) 0123 456" required>
                        </div>
                        <input type="email" placeholder="Enter email" required>
                        <textarea placeholder="Nội dung liên hệ" required></textarea>

                        <div class="form-row">
                            <input type="file">
                            <button type="submit">GỬI YÊU CẦU</button>
                        </div>
                    </form>
                </div>

                <div class="img-right-part3">
                    <img src="${pageContext.request.contextPath}/public/images/banners/banner1.jpg" alt=""
                         style="width: 100%; height: auto;">
                </div>
            </div>
        </section>

    </div>
</div>
<div class="part4">
    <img class="near-header-part4" style="width: 100%; height: 680px;"
         src="${pageContext.request.contextPath}/public/images/banners/1658327764855.png" alt="">
    <div class="listpart4">
        <h1>Xem, chạm và cảm nhận</h1>
        <button style="font-weight: bold;">Tìm cửa hàng</button>
    </div>
</div>
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
            <h3>KANE'S</h3>
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
            <p>Hãy để lại email của bạn để nhận được những ý tưởng trang trí mới và thông tin ưu đãi từ kanes</p>
            <p>Email: kanescare@akacompany.com.vn</p>
            <p>Hotline: 18007200</p>
            <div class="newsletter">
                <input type="email" placeholder="Nhập email của bạn">
                <button class="subscribe-button">ĐĂNG KÝ</button>
            </div>
        </div>
    </div>

</footer>
<!-- </div> -->
</div>
<script>
    AOS.init();
</script>
<script>

</script>
<script src="${pageContext.request.contextPath}/public/js/curtainmenu.js"></script>
<script src="${pageContext.request.contextPath}/public/js/popup.js"></script>
<%--<script src="${pageContext.request.contextPath}/public/js/Cart.js"></script>--%>
<script>
    document.addEventListener("DOMContentLoaded", () => {
        // Lấy URL cần xử lý
        const productUrl = new URL("http://localhost:8080/product?id=1&atributeID=1&cateID=1");

        const productId = productUrl.searchParams.get("id");
        console.log("Product ID:", productId);

        if (productId) {
            fetch("/kenes", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({id: productId}),
            }).then((response) => response.json())
                .then((data) => console.log("Server response:", data))
                .catch((error) => console.error("Error:", error));
        }
    });

</script>
<script src="${pageContext.request.contextPath}/public/js/ProductDetail.js"></script>

</body>
</html>
