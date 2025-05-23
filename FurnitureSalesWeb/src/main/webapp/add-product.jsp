<%--
  Created by IntelliJ IDEA.
  User: KhanhDuy
  Date: 12/19/2024
  Time: 3:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>Add Product</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/admin/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/admin/add-product.css">
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
        <section class="recent">

            <div class="activity-grid-alt5">
                <c:if test="${CMD eq 'add-product'}">
                    <div class="form-container">
                        <!-- Left section: Image upload and button -->
                        <div class="left-section">
                            <div class="img-upload">
                                <label for="productImg">Product Image</label>
                                <div class="img-preview">No Image</div>
                                <input type="file" id="productImg" name="productImg" accept="image/*">
                            </div>
                        </div>

                        <!-- Right section: Product details -->
                        <div class="right-section">

                            <!-- Display error message -->
                            <c:if test="${not empty errorMessage}">
                                <div style="color: red;">
                                        ${errorMessage}
                                </div>
                            </c:if>

                            <!-- Display success message -->
                            <c:if test="${not empty successMessage}">
                                <div style="color: greenyellow;">
                                        ${successMessage}
                                </div>
                            </c:if>

                            <h2>Add New Product</h2>
                            <form method="post" action="/admin/add-product">
                                <!-- Name and Category on the same row -->
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="productName">Product Name</label>
                                        <input type="text" id="productName" name="productName" placeholder="Enter product name" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="productCategory">Product Categories</label>
                                        <select id="productCategory" name="productCategory" required>
                                            <option value="">Select category</option>
                                            <c:if test="${not empty cate}">
                                                <c:forEach var="c" items="${cate}">
                                                    <option value="${c.id}">${c.cateName}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>

                                <!-- Stock and Price on the same row -->
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="material">Material</label>
                                        <input type="text" id="material" name="material" placeholder="Enter product material">
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="price">Price (vnđ)</label>
                                        <input type="text" id="price" name="price" placeholder="Enter product price" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="size">Size</label>
                                        <input type="text" id="size" name="size" placeholder="Enter product size">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="des">Description</label>
                                    <textarea id="des" name="des" placeholder="Enter product description"></textarea>
                                </div>

                                <button type="submit" class="create-btn">Create Product</button>
                            </form>
                        </div>
                    </div>
                </c:if>

                <c:if test="${CMD eq 'edit-product'}">
                    <div class="form-container">
                        <!-- Left section: Image upload and button -->
                        <div class="left-section">
                            <div class="img-upload">
                                <label for="productImg">Product Image</label>
                                <div class="img-preview">No Image</div>
                                <input type="file" id="productImg" name="productImg" accept="image/*">
                            </div>
                        </div>

                        <!-- Right section: Product details -->
                        <div class="right-section">

                            <!-- Display error message -->
                            <c:if test="${not empty errorMessage}">
                                <div style="color: red;">
                                        ${errorMessage}
                                </div>
                            </c:if>

                            <!-- Display success message -->
                            <c:if test="${not empty successMessage}">
                                <div style="color: greenyellow;">
                                        ${successMessage}
                                </div>
                            </c:if>

                            <h2>Update ${product.proName}</h2>
                            <form method="post" action="/admin/edit-product">
                                <!-- Name and Category on the same row -->
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="productName">Product Name</label>
                                        <input type="text" id="productName" name="productName" placeholder="${product.proName}">
                                    </div>
                                    <div class="form-group">
                                        <label for="productCategory">Product Categories</label>
                                        <select id="productCategory" name="productCategory" required>
                                            <option value="">Select category</option>
                                            <c:if test="${not empty cate}">
                                                <c:forEach var="c" items="${cate}">
                                                    <option value="${c.id}" <c:if test="${product.cateID eq c.id}">selected</c:if>>${c.cateName}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>

                                <!-- Stock and Price on the same row -->
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="material">Material</label>
                                        <input type="text" id="material" name="material" placeholder="${pa.material}">
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="price">Price (vnđ)</label>
                                        <input type="text" id="price" name="price" placeholder="<f:formatNumber type="currency" currencySymbol="đ" value="${product.price}"/>">
                                    </div>

                                    <div class="form-group">
                                        <label for="size">Size</label>
                                        <input type="text" id="size" name="size" placeholder="${pa.size}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="des">Description</label>
                                    <textarea id="des" name="des" placeholder="${product.description}"></textarea>
                                </div>

                                <button type="submit" class="create-btn">Update</button>
                            </form>
                        </div>
                    </div>
                </c:if>

                <c:if test="${CMD eq 'add-stock'}">
                    <div class="form-container">
                        <!-- Left section: Image upload and button -->
                        <div class="left-section">
                            <div class="img-upload">
                                <label for="productImg">Product Image</label>
                                <div class="img-preview">No Image</div>
                                <input type="file" id="productImg" name="productImg" accept="image/*">
                            </div>
                        </div>

                        <!-- Right section: Product details -->
                        <div class="right-section">

                            <!-- Display error message -->
                            <c:if test="${not empty errorMessage}">
                                <div style="color: red;">
                                        ${errorMessage}
                                </div>
                            </c:if>

                            <!-- Display success message -->
                            <c:if test="${not empty successMessage}">
                                <div style="color: greenyellow;">
                                        ${successMessage}
                                </div>
                            </c:if>

                            <h2>Update #${product.id} ${product.proName}</h2>
                            <form method="post" action="${action}">
                                <!-- Name and Category on the same row -->
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="stock">Stock</label>
                                        <input type="number" id="stock" name="stock" placeholder="Enter stock quantity" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="wh">Warehouse to import stock</label>
                                        <select id="wh" name="wh" required>
                                            <c:if test="${not empty wh}">
                                                <c:forEach var="c" items="${wh}">
                                                    <option value="${c.id}">
                                                            ${c.name}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>

                                <button type="submit" class="create-btn">Update</button>
                            </form>
                        </div>
                    </div>
                </c:if>

                <c:if test="${CMD eq 'add-category'}">
                    <div class="form-container">
                        <!-- Left section: Image upload and button -->
                        <div class="left-section">
                            <div class="img-upload">
                                <label for="productImg">Product Image</label>
                                <div class="img-preview">No Image</div>
                                <input type="file" id="productImg" name="productImg" accept="image/*">
                            </div>
                        </div>

                        <!-- Right section: Product details -->
                        <div class="right-section">

                            <!-- Display error message -->
                            <c:if test="${not empty errorMessage}">
                                <div style="color: red;">
                                        ${errorMessage}
                                </div>
                            </c:if>

                            <!-- Display success message -->
                            <c:if test="${not empty successMessage}">
                                <div style="color: greenyellow;">
                                        ${successMessage}
                                </div>
                            </c:if>

                            <h2>Create new category</h2>
                            <form method="post" action="/admin/add-category">
                                <!-- Name and Category on the same row -->
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="name">Category Name</label>
                                        <input type="text" id="name" name="name" placeholder="Enter new category" required>
                                    </div>
                                </div>
                                <button type="submit" class="create-btn">Update</button>
                            </form>
                        </div>
                    </div>
                </c:if>

                <c:if test="${CMD eq 'edit-category'}">
                    <div class="form-container">
                        <!-- Left section: Image upload and button -->
                        <div class="left-section">
                            <div class="img-upload">
                                <label for="productImg">Product Image</label>
                                <div class="img-preview">No Image</div>
                                <input type="file" id="productImg" name="productImg" accept="image/*">
                            </div>
                        </div>

                        <!-- Right section: Product details -->
                        <div class="right-section">

                            <!-- Display error message -->
                            <c:if test="${not empty errorMessage}">
                                <div style="color: red;">
                                        ${errorMessage}
                                </div>
                            </c:if>

                            <!-- Display success message -->
                            <c:if test="${not empty successMessage}">
                                <div style="color: greenyellow;">
                                        ${successMessage}
                                </div>
                            </c:if>

                            <h2>Create new category</h2>
                            <form method="post" action="/admin/edit-category">
                                <!-- Name and Category on the same row -->
                                <div class="form-row">
                                    <div class="form-group">
                                        <label for="name">Category Name</label>
                                        <input type="text" id="name" name="name" placeholder="${c.cateName}" required>
                                    </div>
                                </div>
                                <button type="submit" class="create-btn">Update</button>
                            </form>
                        </div>
                    </div>
                </c:if>

            </div>
        </section>
    </main>
</div>

<script src="${pageContext.request.contextPath}/public/js/admin/currency.js"></script>
</body>
</html>
