<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/product.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/footer.css">
    <link href="${pageContext.request.contextPath}/public/bootstrap-5.3.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/public/js/curtainmenu.js"></script>
    <script src="${pageContext.request.contextPath}/public/js/Cart.js"></script>
    <title>Tất cả sản phẩm</title>

</head>

<body>
    <!-- HEADER -->
    <div id="container">
        <div id="notification" class="notification hidden">Sản phẩm đã được thêm vào giỏ hàng!</div>
        <div id="login-notification" class="notification hidden red">Bạn chưa đăng nhập!</div>
        <!-- IMAGE HEADER -->
        <div id="imageHeader">
            <div class="title-bg">
                <div class="title">
                    <Strong style="padding: 10px; font-size: 30px;">Sản Phẩm</Strong><br>
                    <a href="" style="font-weight: normal;">Trang chủ</a> / <a href="../product/All-products.jsp"
                        style="font-weight: bold;">Sản phẩm</a>
                </div>

            </div>
            <div class="mask"></div>
        </div>

    </div>

</body>

</html>