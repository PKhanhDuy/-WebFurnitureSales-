<%@ page import="com.supportjobsearch.dto.OrderDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.supportjobsearch.Bean.Promotion" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.supportjobsearch.enums.PromotionStatus" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/filter-style.css">
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

    <jsp:include page="menu-bar.jsp"/>
</div>

<div class="main-content">

    <c:if test="${CMD eq 'products'}">
        <!-- Pop up -->

        <div id="iframePopup" class="modal" style="display: none">
            <div class="modal-content">
                <span class="close">&times;</span>
                <iframe src="${pageContext.request.contextPath}/views/admin/add-stock.jsp" frameborder="0"></iframe>
            </div>
        </div>

        <!-- End pop up -->
    </c:if>

    <header>
        <div class="search-wrapper">
            <span class="ti-search"></span>
            <input type="search" placeholder="Search">
        </div>

        <div class="social-icons">
            <span class="ti-bell"></span>
            <span class="ti-comment"></span>
            <div></div>
        </div>
    </header>

    <main>
        <c:if test="${CMD eq 'orders'}">

            <h2 class="dash-title">Most Popular Product</h2>
            <h3 class="dash-address">
                <a href="/admin/dashboard">Home</a>/<a href="#">${CMD}</a>
            </h3>

            <div class="dash-cards-alt">
                <div class="card-single">
                    <div class="card-body">
                        <span class="ti-check"></span>
                        <div>
                            <h5>Delivered</h5>
                            <h4>${delivered}</h4>
                        </div>
                    </div>
                </div>

                <div class="card-single">
                    <div class="card-body">
                        <span class="ti-truck"></span>
                        <div>
                            <h5>In Progress</h5>
                            <h4>${delivering}</h4>
                        </div>
                    </div>
                </div>

                <div class="card-single">
                    <div class="card-body">
                        <span class="ti-close"></span>
                        <div>
                            <h5>Cancelled</h5>
                            <h4>${cancelled}</h4>
                        </div>
                    </div>
                </div>

                <div class="card-single">
                    <div class="card-body">
                        <span class="ti-reload"></span>
                        <div>
                            <h5>Pending Payment</h5>
                            <h4>${pending}</h4>
                        </div>
                    </div>
                </div>

                <div class="card-single">
                    <div class="card-body">
                        <span class="ti-package"></span>
                        <div>
                            <h5>Refunded</h5>
                            <h4>${refunded}</h4>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${CMD eq 'discount'}">
            <h2 class="dash-title">Overview</h2>

            <div class="dash-cards-alt">
                <div class="card-single-alt2">
                    <div class="card-body">
                        <span class="ti-check"></span>
                        <div>
                            <h5>Available</h5>
                            <h4>${available}</h4>
                        </div>
                    </div>
                </div>

                <div class="card-single-alt3">
                    <div class="card-body">
                        <span class="ti-close"></span>
                        <div>
                            <h5>Expired</h5>
                            <h4>${expired}</h4>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${CMD eq 'category'}">
            <div class="dash-cards">
                <div class="card-single">
                    <div class="card-body">
                        <img src="${pageContext.request.contextPath}/public/images/admin/category1-714x400.jpg" alt="">
                        <div>
                            <h5>Phòng Khách</h5>
                            <h4>7,306 sản phẩm</h4>
                        </div>
                    </div>
                </div>

                <div class="card-single">
                    <div class="card-body">
                        <img src="${pageContext.request.contextPath}/public/images/admin/category2-581x400.jpg" alt="">
                        <div>
                            <h5>Phòng Ăn</h5>
                            <h4>2,306 sản phẩm</h4>
                        </div>
                    </div>
                </div>

                <div class="card-single">
                    <div class="card-body">
                        <img src="${pageContext.request.contextPath}/public/images/admin/category3-581x400.jpg" alt="">
                        <div>
                            <h5>Phòng Ngủ</h5>
                            <h4>1,146 sản phẩm</h4>
                        </div>
                    </div>
                </div>

            </div>
        </c:if>

        <section class="recent">

            <c:if test="${CMD eq 'products'}">

                <!-- Filter -->
                <div class="filter-container">
                    <div class="filter">
                        <div class="filter-component">
                            <h2>Category:</h2>
                        </div>

                        <!-- Filter Box -->
                        <div class="filter-component">
                            <select id="productCategory" name="productCategory">
                                <option value="">Select category</option>
                                <option value="Kệ phòng khách" selected>Kệ phòng khách</option>
                                <option value="Bàn nước">Bàn nước</option>
                                <option value="Ghế dài & đôn">Ghế dài & đôn</option>
                                <option value="Đèn trang trí">Đèn trang trí</option>
                                <option value="Giường">Giường</option>
                                <option value="Ghế Bar">Ghế Bar</option>
                                <option value="Phòng khách">Phòng khách</option>
                                <option value="Armchair">Armchair</option>
                                <option value="Bàn bên">Bàn bên</option>
                                <option value="Tủ giày">Tủ giày</option>
                                <option value="Tủ ly">Tủ ly</option>
                                <option value="Sofa">Sofa</option>
                                <option value="Đồng hồ trang trí">Đồng hồ trang trí</option>
                                <option value="Tủ áo">Tủ áo</option>
                                <option value="Bình trang trí">Bình trang trí</option>
                                <option value="Khung gương">Khung gương</option>
                                <option value="Bàn làm việc">Bàn làm việc</option>
                                <!-- Add more categories as needed -->
                            </select>
                        </div>
                    </div>

                    <div class="filter">
                        <div class="filter-component">
                            <h2>Warehouse:</h2>
                        </div>

                        <!-- Filter Box -->
                        <div class="filter-component">
                            <select id="inventory" name="inventory">
                                <option value="">Select inventory</option>
                                <option value="WH-001">Hợp Tiến Logistics</option>
                                <option value="WH-002">Noi Bai Cargo Terminal Service JSC</option>
                                <option value="WH-003">SPX Châu Phú</option>
                                <option value="WH-004">SPX Thống Nhất</option>
                                <option value="WH-005">SPX Biên Hòa 01</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="activity-grid-alt2">
                    <div class="activity-card">

                        <div>
                            <div class="search-wrapper">
                                <h3>All Product List</h3>
                                <span class="ti-search"></span>
                                <input type="search" placeholder="Search content here...">
                                <div class="pagination">
                                    <a href="/admin/add-product" class="add-product-btn">Add product</a>
                                </div>

                            </div>
                        </div>

                        <div class="table-responsive">
                            <table id="product-table">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Stock</th>
                                    <th>Category</th>
                                    <th>Rating</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>#001</td>
                                    <td class="td-product">
                                        <img src="${pageContext.request.contextPath}/public/images/all-products/1.jpg"
                                             alt="">
                                        Kệ 3 tầng Gold
                                    </td>
                                    <td>24,480,000₫</td>
                                    <td>486 Item Left
                                        <h4>155 Sold</h4>
                                    </td>
                                    <td>Kệ phòng khách</td>
                                    <td>
                                              <span class="badge rating">
                                                  <span class="ti-star"></span>
                                                  5.0
                                              </span>
                                        <h5>44 reviews</h5>
                                    </td>
                                    <td>
                                        <a href="#" class="openIframePopup">
                                            <span class="ti-plus"></span>
                                        </a>
                                        <a href="edit-product.jsp">
                                            <span class="ti-pencil-alt"></span>
                                        </a>
                                        <a href="#">
                                            <span class="ti-trash"></span>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>#003</td>
                                    <td class="td-product">
                                        <img src="${pageContext.request.contextPath}/public/images/all-products/71.jpg"
                                             alt="">
                                        Bàn nước Dura
                                    </td>
                                    <td>14,900,001₫</td>
                                    <td>66 Item Left
                                        <h4>15 Sold</h4>
                                    </td>
                                    <td>Bàn nước</td>
                                    <td>
                                              <span class="badge rating">
                                                  <span class="ti-star"></span>
                                                  4.0
                                              </span>
                                        <h5>13 reviews</h5>
                                    </td>
                                    <td>
                                        <a href="#" class="openIframePopup">
                                            <span class="ti-plus"></span>
                                        </a>
                                        <a href="edit-product.jsp">
                                            <span class="ti-pencil-alt"></span>
                                        </a>
                                        <a href="#">
                                            <span class="ti-trash"></span>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>#002</td>
                                    <td class="td-product">
                                        <img src="${pageContext.request.contextPath}/public/images/all-products/81.jpg"
                                             alt="">
                                        Đôn Orientale da beige R5
                                    </td>
                                    <td>28,860,000₫</td>
                                    <td>124 Item Left
                                        <h4>18 Sold</h4>
                                    </td>
                                    <td>Kệ phòng khách</td>
                                    <td>
                                              <span class="badge rating">
                                                  <span class="ti-star"></span>
                                                  4.5
                                              </span>
                                        <h5>5 reviews</h5>
                                    </td>
                                    <td>
                                        <a href="#" class="openIframePopup">
                                            <span class="ti-plus"></span>
                                        </a>
                                        <a href="edit-product.jsp">
                                            <span class="ti-pencil-alt"></span>
                                        </a>
                                        <a href="#">
                                            <span class="ti-trash"></span>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>#004</td>
                                    <td class="td-product">
                                        <img src=".${pageContext.request.contextPath}/public/images/all-products/91.jpg"
                                             alt="">
                                        Đèn bàn Gianfranco Gunmetal
                                    </td>
                                    <td>32,880,000₫</td>
                                    <td>1,028 Item Left
                                        <h4>256 Sold</h4>
                                    </td>
                                    <td>Đèn trang trí</td>
                                    <td>
                                              <span class="badge rating">
                                                  <span class="ti-star"></span>
                                                  4.8
                                              </span>
                                        <h5>108 reviews</h5>
                                    </td>
                                    <td>
                                        <a href="#" class="openIframePopup">
                                            <span class="ti-plus"></span>
                                        </a>
                                        <a href="edit-product.jsp">
                                            <span class="ti-pencil-alt"></span>
                                        </a>
                                        <a href="#">
                                            <span class="ti-trash"></span>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>#005</td>
                                    <td class="td-product">
                                        <img src="${pageContext.request.contextPath}/public/images/all-products/11.jpg"
                                             alt="">
                                        Giường Coastal vàng 1m6
                                    </td>
                                    <td>28,900,000₫</td>
                                    <td>128 Item Left
                                        <h4>96 Sold</h4>
                                    </td>
                                    <td>Giường</td>
                                    <td>
                                              <span class="badge rating">
                                                  <span class="ti-star"></span>
                                                  3.7
                                              </span>
                                        <h5>99 reviews</h5>
                                    </td>
                                    <td>
                                        <a href="#" class="openIframePopup">
                                            <span class="ti-plus"></span>
                                        </a>
                                        <a href="edit-product.jsp">
                                            <span class="ti-pencil-alt"></span>
                                        </a>
                                        <a href="#">
                                            <span class="ti-trash"></span>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>#006</td>
                                    <td class="td-product">
                                        <img src="${pageContext.request.contextPath}/public/images/all-products/21.jpg"
                                             alt="">
                                        Tủ ly cao Lake CS6076-4 màu smoke
                                    </td>
                                    <td>28,900,000₫</td>
                                    <td>128 Item Left
                                        <h4>96 Sold</h4>
                                    </td>
                                    <td>Phòng ăn</td>
                                    <td>
                                              <span class="badge rating">
                                                  <span class="ti-star"></span>
                                                  3.7
                                              </span>
                                        <h5>99 reviews</h5>
                                    </td>
                                    <td>
                                        <a href="#" class="openIframePopup">
                                            <span class="ti-plus"></span>
                                        </a>
                                        <a href="edit-product.jsp">
                                            <span class="ti-pencil-alt"></span>
                                        </a>
                                        <a href="#">
                                            <span class="ti-trash"></span>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>#007</td>
                                    <td class="td-product">
                                        <img src="${pageContext.request.contextPath}/public/images/all-products/31.jpg"
                                             alt="">
                                        Ghế Bar Bridge màu nâu Da Cognac
                                    </td>
                                    <td>29,950,001₫</td>
                                    <td>1,242 Item Left
                                        <h4>654 Sold</h4>
                                    </td>
                                    <td>Ghế bar</td>
                                    <td>
                                              <span class="badge rating">
                                                  <span class="ti-star"></span>
                                                  4.3
                                              </span>
                                        <h5>245 reviews</h5>
                                    </td>
                                    <td>
                                        <a href="#" class="openIframePopup">
                                            <span class="ti-plus"></span>
                                        </a>
                                        <a href="edit-product.jsp">
                                            <span class="ti-pencil-alt"></span>
                                        </a>
                                        <a href="#">
                                            <span class="ti-trash"></span>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>#008</td>
                                    <td class="td-product">
                                        <img src="${pageContext.request.contextPath}/public/images/all-products/41.jpg"
                                             alt="">
                                        Sofa 2 chỗ Mây mới
                                    </td>
                                    <td>19,900,000₫</td>
                                    <td>12 Item Left
                                        <h4>32 Sold</h4>
                                    </td>
                                    <td>Phòng khách</td>
                                    <td>
                                              <span class="badge rating">
                                                  <span class="ti-star"></span>
                                                  4.3
                                              </span>
                                        <h5>23 reviews</h5>
                                    </td>
                                    <td>
                                        <a href="#" class="openIframePopup">
                                            <span class="ti-plus"></span>
                                        </a>
                                        <a href="edit-product.jsp">
                                            <span class="ti-pencil-alt"></span>
                                        </a>
                                        <a href="#">
                                            <span class="ti-trash"></span>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>#009</td>
                                    <td class="td-product">
                                        <img src="${pageContext.request.contextPath}/public/images/all-products/51.jpg"
                                             alt="">
                                        Armchair Doulton vintage
                                    </td>
                                    <td>28,500,000₫</td>
                                    <td>12 Item Left
                                        <h4>32 Sold</h4>
                                    </td>
                                    <td>Armchair</td>
                                    <td>
                                              <span class="badge rating">
                                                  <span class="ti-star"></span>
                                                  4.3
                                              </span>
                                        <h5>23 reviews</h5>
                                    </td>
                                    <td>
                                        <a href="#" class="openIframePopup">
                                            <span class="ti-plus"></span>
                                        </a>
                                        <a href="edit-product.jsp">
                                            <span class="ti-pencil-alt"></span>
                                        </a>
                                        <a href="#">
                                            <span class="ti-trash"></span>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>#010</td>
                                    <td class="td-product">
                                        <img src="${pageContext.request.contextPath}/public/images/all-products/61.jpg"
                                             alt="">
                                        Bàn bên 3C-02
                                    </td>
                                    <td>7,990,000₫</td>
                                    <td>12 Item Left
                                        <h4>32 Sold</h4>
                                    </td>
                                    <td>Bàn bên</td>
                                    <td>
                                              <span class="badge rating">
                                                  <span class="ti-star"></span>
                                                  4.3
                                              </span>
                                        <h5>23 reviews</h5>
                                    </td>
                                    <td>
                                        <a href="#" class="openIframePopup">
                                            <span class="ti-plus"></span>
                                        </a>
                                        <a href="edit-product.jsp">
                                            <span class="ti-pencil-alt"></span>
                                        </a>
                                        <a href="#">
                                            <span class="ti-trash"></span>
                                        </a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="pagination">
                            <h6 class="show-entries">*Showing 1 to 10 of 152 entries</h6>
                            <a href="#">&laquo;</a>
                            <a href="#" class="active">1</a>
                            <a href="#">2</a>
                            <a href="#">...</a>
                            <a href="#">15</a>
                            <a href="#">16</a>
                            <a href="#">&raquo;</a>
                        </div>

                    </div>
                </div>

            </c:if>

            <c:if test="${CMD eq 'orders'}">
                <div class="activity-grid-alt2">
                    <div class="activity-card">

                        <%
                            int size = (int) request.getAttribute("total");
                            int startIndex = 0;
                            int currentPages = 1;
                            int showMax = 5;
                            int totalPages = (int) Math.ceil((double) size / (double) showMax);
                            String pageParam = request.getParameter("page");

                            if (pageParam != null) {
                                currentPages = Integer.parseInt(pageParam);
                            }

                            startIndex = (currentPages - 1) * showMax;
                            int endIndex = Math.min(startIndex + showMax, size);
                        %>

                        <div>
                            <div class="search-wrapper">
                                <h3>All Orders List</h3>
                                <span class="ti-search"></span>
                                <input type="search" placeholder="Search content here...">
                            </div>
                        </div>

                        <div class="table-responsive">
                            <table>
                                <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>Created at</th>
                                    <th>Customer</th>
                                    <th>Total</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${not empty orders}">

                                    <%
                                        List<OrderDto> orders = (List<OrderDto>) request.getAttribute("orders");
                                        for (int i = startIndex; i < endIndex; i++) {
                                            OrderDto u = orders.get(i);
                                    %>
                                    <tr>
                                        <td><%=u.getId()%>
                                        </td>
                                        <td><%=u.getTimestamp()%>
                                        </td>
                                        <td><%=u.getCustomer()%>
                                        </td>
                                        <td><%=u.getTotalF()%>
                                        </td>
                                        <td>
                                            <%
                                                var status = u.getStatus();
                                                switch (status) {
                                                    case Completed: {
                                            %>
                                            <span class="badge success">
                                                    <span class="ti-check"></span>
                                                    Completed
                                                </span>
                                            <%
                                                    break;
                                                }
                                                case Packaging: {

                                            %>
                                            <span class="badge warning">
                                                    <span class="ti-package"></span>
                                                    Packaging...
                                                </span>
                                            <%
                                                    break;
                                                }
                                                case Delivering: {
                                            %>
                                            <span class="badge warning">
                                                    <span class="ti-truck"></span>
                                                    Delivering
                                                </span>
                                            <%
                                                    break;
                                                }
                                                case Cancelled: {
                                            %>
                                            <span class="badge alert">
                                                    <span class="ti-close"></span>
                                                    Cancelled
                                                </span>
                                            <%
                                                    }
                                                }
                                            %>
                                        </td>
                                        <td>
                                            <a href="edit-order?id=<%=u.getId()%>">
                                                <span class="ti-pencil-alt"></span>
                                            </a>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </c:if>

                                </tbody>
                            </table>
                        </div>

                        <div class="pagination">
                            <h6 class="show-entries">
                                *Showing <%=startIndex + 1%> to <%=endIndex%> of <%=size%> entries
                            </h6>
                            <a href="?page=<%=currentPages > 1 ? currentPages - 1 : 1 %>">&laquo;</a>
                            <%
                                boolean needDot = false;
                                for (int i = 1; i <= totalPages; i++) {
                                    String activeClass = (i == currentPages) ? "active" : "";
                                    if (i == 1 || i == totalPages) {
                                        needDot = true;
                            %>
                            <a href="?page=<%=i%>" class="<%=activeClass%>">
                                <%=i%>
                            </a>
                            <%
                            } else if (i < currentPages + 2 && i > currentPages - 2) {
                                needDot = true;
                            %>
                            <a href="?page=<%=i%>" class="<%=activeClass%>">
                                <%=i%>
                            </a>
                            <%
                            } else if (needDot) {
                                needDot = false;
                            %>
                            <a href="#" class="">...</a>
                            <%
                                    }
                                }
                            %>
                            <a href="?page=<%=currentPages < totalPages ? currentPages + 1 : totalPages %>">&raquo;</a>
                        </div>

                    </div>
                </div>
            </c:if>

            <c:if test="${CMD eq 'discount'}">
                <div class="activity-grid-alt2">
                    <div class="activity-card">

                        <%
                            int size = (int) request.getAttribute("total");
                            int startIndex = 0;
                            int currentPages = 1;
                            int showMax = 5;
                            int totalPages = (int) Math.ceil((double) size / (double) showMax);
                            String pageParam = request.getParameter("page");

                            if (pageParam != null) {
                                currentPages = Integer.parseInt(pageParam);
                            }

                            startIndex = (currentPages - 1) * showMax;
                            int endIndex = Math.min(startIndex + showMax, size);
                        %>

                        <div>
                            <div class="search-wrapper">
                                <h3>All Discount List</h3>
                                <span class="ti-search"></span>
                                <input type="search" placeholder="Search content here...">
                                <div class="pagination">
                                    <a href="/admin/add-discount" class="add-product-btn">Create a discount plan</a>
                                </div>
                            </div>
                        </div>

                        <div class="table-responsive">
                            <table>
                                <thead>
                                <tr>
                                    <th>Code</th>
                                    <th>Discount</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${not empty discounts}">

                                    <%
                                        NumberFormat formater = NumberFormat.getInstance(Locale.ENGLISH);
                                        List<Promotion> orders = (List<Promotion>) request.getAttribute("discounts");
                                        for (int i = startIndex; i < endIndex; i++) {
                                            Promotion u = orders.get(i);
                                    %>
                                    <tr>
                                        <td><%=u.getCodes()%>
                                        </td>
                                        <td><%=u.getValueOfPro() < 101 ? u.getValueOfPro() + "%" : formater.format(u.getValueOfPro())%>
                                        </td>
                                        <td><%=Timestamp.valueOf(u.getStartDate())%>
                                        </td>
                                        <td><%=Timestamp.valueOf(u.getEndDate())%>
                                        </td>
                                        <td>
                                            <%
                                                var status = u.getStatusOfPro();
                                                switch (status) {
                                                    case AVAILABLE: {
                                            %>
                                            <span class="badge success">
                                                    <span class="ti-check"></span>
                                                    Active
                                                </span>
                                            <%
                                                    break;
                                                }
                                                case EXPIRED: {

                                            %>
                                            <span class="badge warning">
                                                    <span class="ti-close"></span>
                                                    Expired
                                                </span>
                                            <%
                                                        break;
                                                    }
                                                }
                                            %>
                                        </td>
                                        <td>
                                            <a href="edit-promo?id=<%=u.getId()%>">
                                                <span class="ti-pencil-alt"></span>
                                            </a>
                                            <a href="#">
                                                <span class="ti-trash"></span>
                                            </a>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </c:if>

                                </tbody>
                            </table>
                        </div>

                        <div class="pagination">
                            <h6 class="show-entries">
                                *Showing <%=startIndex + 1%> to <%=endIndex%> of <%=size%> entries
                            </h6>
                            <a href="?page=<%=currentPages > 1 ? currentPages - 1 : 1 %>">&laquo;</a>
                            <%
                                boolean needDot = false;
                                for (int i = 1; i <= totalPages; i++) {
                                    String activeClass = (i == currentPages) ? "active" : "";
                                    if (i == 1 || i == totalPages) {
                                        needDot = true;
                            %>
                            <a href="?page=<%=i%>" class="<%=activeClass%>">
                                <%=i%>
                            </a>
                            <%
                            } else if (i < currentPages + 2 && i > currentPages - 2) {
                                needDot = true;
                            %>
                            <a href="?page=<%=i%>" class="<%=activeClass%>">
                                <%=i%>
                            </a>
                            <%
                            } else if (needDot) {
                                needDot = false;
                            %>
                            <a href="#" class="">...</a>
                            <%
                                    }
                                }
                            %>
                            <a href="?page=<%=currentPages < totalPages ? currentPages + 1 : totalPages %>">&raquo;</a>
                        </div>

                    </div>
                </div>
            </c:if>

            <c:if test="${CMD eq 'payment'}">
                <div class="activity-grid-alt2">
                    <div class="activity-card">

                        <%
                            int size = (int) request.getAttribute("total");
                            int startIndex = 0;
                            int currentPages = 1;
                            int showMax = 10;
                            int totalPages = (int) Math.ceil((double) size / (double) showMax);
                            String pageParam = request.getParameter("page");

                            if (pageParam != null) {
                                currentPages = Integer.parseInt(pageParam);
                            }

                            startIndex = (currentPages - 1) * showMax;
                            int endIndex = Math.min(startIndex + showMax, size);
                        %>

                        <div>
                            <div class="search-wrapper">
                                <h3>All Payment List</h3>
                                <span class="ti-search"></span>
                                <input type="search" placeholder="Search content here...">
                            </div>
                        </div>

                        <div class="table-responsive">
                            <table>
                                <thead>
                                <tr>
                                    <th>Customer ID</th>
                                    <th>Order ID</th>
                                    <th>Total</th>
                                    <th>Payment Method</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${not empty payments}">

                                    <%
                                        NumberFormat formater = NumberFormat.getInstance(Locale.ENGLISH);
                                        List<OrderDto> orders = (List<OrderDto>) request.getAttribute("payments");
                                        for (int i = startIndex; i < endIndex; i++) {
                                            OrderDto u = orders.get(i);
                                    %>
                                    <tr>
                                        <td><%=u.getCustomer()%>
                                        </td>
                                        <td><%=u.getId()%>
                                        </td>
                                        <td><%=u.getTotalF()%>
                                        </td>
                                        <td><%=u.getMethod().equals("BANK") ? "BANK" : u.getMethod()%>
                                        </td>
                                        <td>
                                            <%
                                                var status = u.getStatuss();
                                                switch (status) {
                                                    case COMPLETED: {
                                            %>
                                            <span class="badge success">
                                                    <span class="ti-check"></span>
                                                    Completed
                                                </span>
                                            <%
                                                    break;
                                                }
                                                case PENDING: {

                                            %>
                                            <span class="badge warning">
                                                    <span class="ti-truck"></span>
                                                    Pending...
                                                </span>
                                            <%
                                                    break;
                                                }
                                                case CANCELLED: {

                                            %>
                                            <span class="badge alert">
                                                    <span class="ti-close"></span>
                                                    Cancelled
                                                </span>
                                            <%
                                                        break;
                                                    }
                                                }
                                            %>
                                        </td>
                                        <td>
                                            <a href="edit-order?id=<%=u.getId()%>">
                                                <span class="ti-pencil-alt"></span>
                                            </a>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </c:if>

                                </tbody>
                            </table>
                        </div>

                        <div class="pagination">
                            <h6 class="show-entries">
                                *Showing <%=startIndex + 1%> to <%=endIndex%> of <%=size%> entries
                            </h6>
                            <a href="?page=<%=currentPages > 1 ? currentPages - 1 : 1 %>">&laquo;</a>
                            <%
                                boolean needDot = false;
                                for (int i = 1; i <= totalPages; i++) {
                                    String activeClass = (i == currentPages) ? "active" : "";
                                    if (i == 1 || i == totalPages) {
                                        needDot = true;
                            %>
                            <a href="?page=<%=i%>" class="<%=activeClass%>">
                                <%=i%>
                            </a>
                            <%
                            } else if (i < currentPages + 2 && i > currentPages - 2) {
                                needDot = true;
                            %>
                            <a href="?page=<%=i%>" class="<%=activeClass%>">
                                <%=i%>
                            </a>
                            <%
                            } else if (needDot) {
                                needDot = false;
                            %>
                            <a href="#" class="">...</a>
                            <%
                                    }
                                }
                            %>
                            <a href="?page=<%=currentPages < totalPages ? currentPages + 1 : totalPages %>">&raquo;</a>
                        </div>

                    </div>
                </div>
            </c:if>

            <c:if test="${CMD eq 'category'}">
                <div class="activity-grid-alt2">
                    <div class="activity-card">
                        <div>
                            <div class="search-wrapper">
                                <h3>All Category List</h3>
                                <div class="pagination">
                                    <a href="/admin/add-category" class="add-product-btn">Add New Category</a>
                                </div>
                            </div>
                        </div>

                        <div class="table-responsive">
                            <table>
                                <thead>
                                <tr>
                                    <th>Categories</th>
                                    <th>Starting Price</th>
                                    <th>ID</th>
                                    <th>Product Stock</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${not empty categories}">
                                    <c:forEach var="c" items="${categories}">
                                        <tr>
                                            <td class="td-product">
                                                <img src="/${c.thumb}" alt="">
                                                    ${c.category_name}
                                            </td>
                                            <td>${c.priceF}₫</td>
                                            <td>${c.categoryid}</td>
                                            <td>${c.product_stock}</td>
                                            <td>
                                                <a href="/admin/edit-category?id=${c.categoryid}">
                                                    <span class="ti-pencil-alt"></span>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </c:if>

        </section>
    </main>

</div>
<script src="${pageContext.request.contextPath}/public/js/admin/jquery.min.js"></script>
<c:if test="${CMD eq 'products'}">
    <script src="${pageContext.request.contextPath}/public/js/admin/product.js"></script>
</c:if>
<%--<script src="${pageContext.request.contextPath}/public/js/admin/popup.js"></script>--%>
</body>
</html>
