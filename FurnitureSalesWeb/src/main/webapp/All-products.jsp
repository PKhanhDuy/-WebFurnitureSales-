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

        <!-- FILTER -->
        <div id="filter">
            <div class="row">
                <div class="col-md-2">
                    <div class="row">
                        <h6>Giá</h6>
                        <div class="dropdown-price">
                            <div id="sort-filter" class="dropdown-toggle-price" onclick="toggleDropdownPrice()">Tất cả
                            </div>
                            <div class="dropdown-menu-price">
                                <div class="dropdown-item-price selected" onclick="selectItem(this)">Tất cả</div>
                                <div class="dropdown-item-price" onclick="selectItem(this)">Mới nhất</div>
                                <div class="dropdown-item-price" onclick="selectItem(this)">Thấp đến cao</div>
                                <div class="dropdown-item-price" onclick="selectItem(this)">Cao đến thấp</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-2">
                    <div class="row">
                        <h6>Chât liệu</h6>
                        <div class="dropdown-material">
                            <div id="material-filter" class="dropdown-toggle-material"
                                onclick="toggleDropdownmaterial()">Tất cả</div>
                            <div class="dropdown-menu-material">
                                <div class="dropdown-item-material">
                                    <input type="checkbox" id="metal" onchange="updateSelection()">
                                    <label for="metal">Kim loại</label>
                                </div>
                                <div class="dropdown-item-material">
                                    <input type="checkbox" id="wood" onchange="updateSelection()">
                                    <label for="wood">Gỗ</label>
                                </div>
                                <div class="dropdown-item-material">
                                    <input type="checkbox" id="glass" onchange="updateSelection()">
                                    <label for="glass">Thủy tinh</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="applyBtn">
                        <button class="filter">Áp dụng</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- PRODUCTS -->
        <div id="p-product">
            <div class="container mt-5">
                <div id="product-area" class="row">
                    <c:forEach var="p" items="${products}">
                        <div class="col-md-3">
                            <div class="card product-card product" data-id="${p.id}" data-name="${p.proName}"
                                data-img="${p.thumb}" data-price="${p.price}">
                                <a href="product?id=${p.id}&atributeID=${p.atributeID}&cateID=${p.cateID}">
                                    <img src="${p.thumb}" class="image-top" alt="${p.proName}">
                                    <img src="${p.thumb}" class="image-back" alt="${p.proName}">
                                </a>
                                <div class="card-body">
                                    <h6 class="product-name">${p.proName}</h6>
                                    <div class="like-price-product favourite-product" data-id="${p.id}"
                                        data-user="${sessionScope.auth.id}">
                                        <span class="product-price">
                                            <f:formatNumber type="currency" currencySymbol="đ" value="${p.price}" />
                                        </span>
                                        <button class="wishlist-button">
                                            <i class="bi bi-heart"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="cart-see-more-btns">
                                    <div class="row">
                                        <div class="col-sm-7 col-md-7">
                                            <div class="cart-btn use-button fake-btn" style="border: none">
                                                <button class="add-to-cart"
                                                    style="font-size: 11px; font-weight: bold;padding: 10px 5px">
                                                    THÊM VÀO GIỎ
                                                </button>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 col-md-5">
                                            <div class="use-button fake-btn">
                                                <%-- <a
                                                    href="product?id=${p.id}&atributeID=${p.atributeID}&cateID=${p.cateID}">--%>
                                                    <a href="javascript:void(0);"
                                                        onclick="showProductDetails(${p.id}, ${p.atributeID}, ${p.cateID})">
                                                        <p>XEM THÊM</p>
                                                    </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>

</body>

</html>