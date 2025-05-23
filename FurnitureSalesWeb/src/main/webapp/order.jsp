<%--
  Created by IntelliJ IDEA.
  User: dttan
  Date: 12/18/2024
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/order.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/footer.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/public/js/Cart.js"></script>
    <script src="${pageContext.request.contextPath}/public/js/Promotion.js"></script>


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
                            <button class="remove-item" data-id="${cp.id}"
                                    style="border: none; background-color: white;">
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
                                                          value="${sessionScope.cart.total}"/>
                    </p>
                </div>
            </div>
            <div class="watch-cart center-items">
                <a href="${pageContext.request.contextPath}/CartController">XEM GIỎ HÀNG</a>
            </div>
            <div class="check-out center-items">
                <a href="">THANH TOÁN</a>
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
                <a href="">0906 904 114</a>
                <div class="about-hd">
                    <a href="">Giới thiệu</a>
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
                <a href="${pageContext.request.contextPath}/kenes"><img
                        src="${pageContext.request.contextPath}/public/images/logos/logo3.png" alt="Logo">
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
            <form action="/search" method="get">
                <div class="search-bar-hd">
                    <input id="search-input" name="search-input" type="text" placeholder="Tìm sản phẩm">
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


<div class="container" style="margin-top: 2%;">
    <div class="shipping-address" , style="border: none;">
        <h2>Tóm tắt đơn hàng</h2>
        <form>


            <h3>Sản phẩm</h3>
            <div class="product-list"
                 style="max-height: 400px; overflow-y: auto; border: 1px solid #ddd; padding: 10px;">
                <c:forEach var="cp" items="${sessionScope.cart.list}">
                    <div class="product" style="margin-bottom: 20px;">
                        <img src="${pageContext.request.contextPath}/${cp.img}"
                             alt="${cp.name}"
                             style="width: 100px; height: auto; object-fit: cover;">

                        <div class="product-details" style="display: flex; align-items: center; gap: 200px;">
                            <div class="product-column" style="flex: 1;">
                                <p><strong style="width: 150px">${cp.name}</strong></p>
                                <p>Số lượng ${cp.quantity}</p>
                                <p style="font-weight: bold; white-space: nowrap;">
                                    Giá gốc: <fmt:formatNumber value="${cp.price  *cp.quantity}"
                                                               type="number"
                                                               groupingUsed="true"/> đ
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <%--                <c:forEach var="order" items="${orderitems}">--%>
                <%--                    <div class="product" style="margin-bottom: 20px;">--%>
                <%--                        <img src="${pageContext.request.contextPath}/${order.product.thumb}"--%>
                <%--                             alt="${order.product.proName}"--%>
                <%--                             style="width: 100px; height: auto; object-fit: cover;">--%>

                <%--                        <div class="product-details" style="display: flex; align-items: center; gap: 200px;">--%>
                <%--                            <div class="product-column" style="flex: 1;">--%>
                <%--                                <p><strong style="width: 150px">${order.product.proName}</strong></p>--%>
                <%--                                <p>Số lượng ${order.amount}</p>--%>
                <%--                                <p style="font-weight: bold; white-space: nowrap;">--%>
                <%--                                    Giá gốc: <fmt:formatNumber value="${order.product.price  *order.amount}"--%>
                <%--                                                               type="number"--%>
                <%--                                                               groupingUsed="true"/> đ--%>
                <%--                                </p>--%>
                <%--                            </div>--%>
                <%--                        </div>--%>
                <%--                    </div>--%>
                <%--                </c:forEach>--%>
            </div>

            <div class="summary-item">
                <p>Thành tiền</p>
                <div class="col-sm-6 col-md-6 t-bold total-price">
                    <p id="total-after-promotion" class="total-cart">
                        <c:if test="${sessionScope.auth == null}">
                            <f:formatNumber type="number" currencySymbol="đ" value="0.0"/>
                        </c:if>
                        <c:if test="${sessionScope.auth != null}">
                            <f:formatNumber type="number" currencySymbol="đ"
                                            value="${sessionScope.valueOfPromotion}"/>
                        </c:if>
                    </p>
                </div>
            </div>
            <h3>THÔNG TIN THÊM</h3>
            <label>Lưu ý cho đơn hàng (tùy chọn)</label>
            <textarea
                    placeholder="Viết các lưu ý cho đơn hàng của bạn, ví dụ: lưu ý đặc biệt khi vận chuyển."></textarea>

            <h3>PHƯƠNG THỨC THANH TOÁN</h3>
            <form id="payment">
                <div class="payment-method">
                    <button type="submit" class="pay-bank">Chuyển khoản ngân hàng</button>
                    <button type="submit" class="cod">Thanh toán khi nhận hàng</button>
                </div>
            </form>
            <script>
                document.getElementById('payment').addEventListener('submit', function (event) {
                    const selectedButton = document.querySelector('.payment-method button.selected');

                    if (!selectedButton) {
                        event.preventDefault();
                        alert('Bạn cần chọn phương thức thanh toán');
                    } else if (!checkbox.checked) {
                        event.preventDefault();
                    } else {
                        alert("Thanh toán thành công!");
                    }
                });

                document.querySelector('.pay-bank').addEventListener('click', function () {
                    this.classList.add('selected');
                    document.querySelector('.cod').classList.remove('selected');
                });

                document.querySelector('.cod').addEventListener('click', function () {
                    this.classList.add('selected');
                    document.querySelector('.pay-bank').classList.remove('selected');
                });

            </script>
        </form>
    </div>

    <div class="order-summary" ; style="border: none;">
        <h2>Địa chỉ giao hàng</h2>
        <div class="shipping-address-container">
            <div>${address[0].user.fullName} (+84) ${address[0].user.phoneNum}</div>
            <div>${address[0].address.fullAddress}</div>

        </div>


        <form id="abc" method="POST" style="margin-top: 20px;padding:10px" action="/order">
            <div class="actions">
                <p4><strong>Địa chỉ mới</strong></p4>
            </div>
            <label>Họ và tên *</label>
            <input style="width: 400px;height: 50px" type="text" name="name" placeholder="Nhập họ tên của bạn"
                   required>

            <label>Số điện thoại *</label>
            <input type="text" style="width: 400px;height: 50px" name="phone" placeholder="Nhập số điện thoại của bạn">

            <label for="province-select">Tỉnh / Thành phố *</label>
            <select name="city" id="province-select" style="width: 400px;height: 50px" required>
                <option value="">Chọn tỉnh / thành phố</option>
            </select>

            <label for="district-select">Quận / Huyện *</label>
            <select name="district" id="district-select" style="width: 400px;height: 50px" required>
                <option value="">Chọn quận / huyện</option>
            </select>

            <label for="address">Địa chỉ *</label>
            <input type="text" id="address" name="address" style="width: 400px;height: 50px" placeholder="Nhập địa chỉ"
                   required>

            <%--            <label class="checkbox-label">--%>
            <%--                <input type="checkbox" name="createAccount"> Create an account?--%>
            <%--            </label>--%>

            <div class="modal-footer" style="padding: 5px;margin: auto">
                <button type="button" style="background: orangered; color: white; margin-right: 10px"
                        class="btn btn-cancel">Hủy
                </button>
                <button type="submit" style="background: orangered; color: white" class="btn btn-confirm">Xác nhận
                </button>
            </div>
        </form>


        <h3>CHÍNH SÁCH BÁN HÀNG</h3>
        <div class="policy">
            <div data-bs-spy="scroll" data-bs-target="#list-example" data-bs-offset="0" class="scrollspy-example"
                 tabindex="0">
                <div id="col-1787990321" class="col small-12 large-12">
                    <div class="col-inner">

                        <p></p>
                        <div id="content" class="content-area" role="main">
                            <p><strong>CHÍNH SÁCH BÁN HÀNG</strong></p>
                            <p><strong>1<span style="font-size: 14.4px">.</span> Thanh toán</strong></p>
                            <p>Kanes chỉ áp dụng duy nhất một hình thức thanh toán online qua thẻ tín dụng,
                                chuyển khoản, cụ thể:</p>
                            <ul>
                                <li>Bước 1: Khách hàng tìm hiểu thông tin về sản phẩm.</li>
                                <li>Bước 2: Khách hàng xác thực đơn hàng (điện thoại, tin nhắn, email).</li>
                                <li>Bước 3: Khách hàng đồng ý các điều khoản mua hàng, chi phí giao hàng và lắp đặt.
                                </li>
                                <li>Bước 4: Khách hàng thanh toán qua các thẻ ATM/VISA/CREDIT CARD.</li>
                                <li>Bước 5: Hệ thống Kanes xác nhận thông tin khách hàng và giao hàng.</li>
                                <li>Bước 6: Khách hàng nhận hàng, kiểm tra và xác nhận với nhân viên.</li>
                            </ul>
                            <p><strong>2. Chính sách lưu kho (Bên mua gửi/ lưu giữ hàng hóa tại kho của bên
                                bán).</strong></p>
                            <p>Thỏa thuận lưu kho, phí lưu kho và điều kiện lưu kho sẽ được ký kết nếu khách hàng có
                                nhu cầu kí gửi hàng hóa.</p>
                            <p>3<strong>. ĐƠN HÀNG CÓ HIỆU LỰC.</strong></p>
                            <p>Đơn hàng có hiệu lực là đơn hàng sau khi Quý khách đã thanh toán và nhận được xác
                                nhận của nhân viên bán hàng Kanes.</p>
                            <p>Quý khách không được hủy đơn hàng sau khi đã thanh toán và nhận được xác nhận của
                                nhân viên bán hàng Kanes.</p>
                            <p>4<strong>. QUY ĐỊNH ĐỔI TRẢ HÀNG HÓA.</strong></p>
                            <p>4.1. Các trường hợp h<strong>ợp được đổi hàng:</strong></p>
                            <p>Kanes sẽ thực hiện việc đổi, trả hàng hóa theo các điều kiện sau:</p>
                            <ul>
                                <li>Hàng không đủ số lượng, đủ bộ, hoặc không đúng chủng loại theo như trong đơn đặt
                                    hàng của Quý khách.
                                </li>
                                <li>Hàng bị lỗi, không đạt chất lượng.</li>
                            </ul>
                            <p><strong>4.2. Các trường hợp KHÔNG được đổi trả hàng hóa:</strong></p>
                            <ul>
                                <li>– Sản phẩm đã sử dụng, dơ bẩn, hoặc hư hại.</li>
                                <li>– Sản phẩm không có đầy đủ chứng từ, hóa đơn.</li>
                                <li>– Sản phẩm mua theo các diện thanh lý, khuyến mại, bán hiện trạng.</li>
                                <li>– Sản phẩm đã được Quý khách ký vào biên bản nhận hàng.</li>
                            </ul>
                            <p><strong>CHÍNH SÁCH GIAO HÀNG</strong></p>
                            <p>1.<strong> PHẠM VI ÁP DỤNG</strong></p>
                            <p>Tất cả các đơn hàng có nhu cầu giao hàng và lắp đặt tại nhà được đặt mua theo hình
                                thức mua online (qua website Kanes.com, điện thoại, fanpage…).</p>
                            <p><strong>2. THỜI GIAN GIAO HÀNG.</strong></p>
                            <p>– Chính sách giao hàng được áp dụng trong giờ hành chính từ thứ 2 đến hết thứ 7 (trừ
                                chủ nhật, ngày lễ, tết).</p>
                            <p>– Đối với các đơn hàng có sản phẩm cần lắp đặt, nhân viên giao hàng và kỹ thuật sẽ
                                đến tận nhà lắp đặt cho Quý khách.</p>
                            <p>– Đơn hàng giao tại nội thành Hà Nội, Hồ Chí Minh:</p>
                            <p>• Đối với các sản phẩm có sẵn tại kho và showroom: khách hàng đặt hàng từ 09h-17h sẽ
                                được giao vào ngày hôm sau.</p>
                            <p>• Đối với đơn hàng phát sinh sau 17h: sẽ được giao trong 02 ngày làm việc.</p>
                            <p>• Đối với các sản phẩm đặt hàng: thời gian giao hàng sẽ được thỏa thuận giữa hai bên.
                            </p>
                            <p>– Đơn hàng đi tỉnh hoặc khu vực ngoại thành Hà Nội, Hồ Chí Minh sẽ được hoàn tất
                                trong 02 – 04 ngày làm việc (trừ trường hợp chờ hàng).</p>
                            <p>– Lịch giao hàng trong thời gian diễn ra chương trình khuyến mại sẽ được nhân viên
                                chúng tôi thông báo cụ thể tới quý khách.</p>
                            <p><strong>3. BIỂU PHÍ GIAO HÀNG – LẮP ĐẶT.</strong></p>
                            <p>– Biểu phí giao hàng bao gồm chi phí vận chuyển và chi phí nhân công giao hàng – lắp
                                đặt (nếu có và đã bao gồm VAT).</p>
                            <p>– Áp dụng lắp đặt cho các nhóm hàng nội thất. Không bao gồm nhóm đèn điện và các nhóm
                                hàng khác.</p>
                            <p>– Biểu phí giao hàng:</p>
                            <p>• Đơn hàng dưới 10 triệu đồng: 350.000đ/ 1 chuyến trong nội thành Hà Nội và Hồ Chí
                                Minh.</p>
                            <p>• Đơn hàng từ 10 triệu đồng trở lên: miễn phí vận chuyển và lắp đặt trong nội thành
                                Hà Nội và Hồ Chí Minh.</p>
                            <p>• Đơn hàng đi tỉnh hoặc khu vực ngoại thành Hà Nội, Hồ Chí Minh sẽ được thông báo mức
                                phí cụ thể tới Quý khách sau khi Kanes tiếp nhận thông tin đơn hàng.</p>
                            <p>– Lưu ý: Các loại phụ phí khác như lưu kho, phí khoan tường, phí tháo dỡ, phí giao
                                tới địa điểm không thuận lợi như trên tầng/ lầu cao không có hoặc không sử dụng được
                                thang máy; hoặc khu vực lắp đặt phải thuê xe cẩu đưa hàng hóa lên cao; hoặc mặt bằng
                                khu vực lắp đặt không đảm bảo điều kiện có phát sinh chi phí vận chuyển; hoặc các
                                trường hợp khác,… Chúng tôi sẽ thông báo mức phí cụ thể tới quý khách.</p>
                            <p>4. <strong>CÁC ĐIỀU KIỆN KHÁC.</strong></p>
                            <p>– Quý khách vui lòng kiểm tra hàng hóa trước khi ký nhận hàng hóa. Quý khách có thể
                                từ chối nhận hàng nếu hàng hóa đã bị vỡ hỏng do quá trình vận chuyển hoặc Quý khách
                                liên hệ trực tiếp với nhân viên bán hàng để được tư vấn cụ thể.</p>
                            <p>– Trường hợp công ty đã vận chuyển đến địa điểm giao nhận như thỏa thuận, nhưng vì
                                một lý do khách hàng thay đổi địa điểm nhận hàng; khách hàng phải chịu chi phí vận
                                chuyển cho việc đổi địa điểm giao hàng theo biểu phí của công ty. Mức phí này sẽ
                                được nhân viên Kanes thông báo đến Quý khách.</p>
                            <p>– Trong trường hợp khách hàng có thay đổi về thời gian nhận hàng so với thỏa thuận
                                khi mua hàng, cần báo trước cho chúng tôi ít nhất trước 24h với khách hàng nội thành
                                và ít nhất 48h đối với khách hàng có khoảng cách trên 30km từ showroom mua hàng.</p>
                            <p>– Trường hợp công ty đã vận chuyển đến địa điểm giao nhận nhưng không liên lạc được
                                với quý khách, chúng tôi sẽ để lại tin nhắn thông báo. Chúng tôi sẽ tính phí giao
                                hàng nếu giao hàng không thành công.</p>
                            <p>– Trong một số trường hợp khách quan chúng tôi có thể giao hàng chậm do điều kiện bất
                                khả kháng: thời tiết xấu, thiên tai, điều kiện giao thông, giờ cấm đường hoặc vào
                                thời điểm lễ tết, sự kiện đặc biệt dẫn tới thay đổi thời gian giao hàng, công ty sẽ
                                liên lạc thông báo và điều chỉnh thời gian giao hàng cụ thể cho khách hàng.</p>
                            <p>– Nhân viên của chúng tôi chỉ lắp đặt sản phẩm trong điều kiện mặt bằng sử dụng đã
                                sẵn sàng. Nhân viên không được phép thực hiện bất kỳ công việc nào khác ngoài việc
                                lắp đặt sản phẩm.</p>
                            <p>– Nhân viên giao hàng – lắp đặt không được phép thu thêm bất cứ khoản chi phí nào
                                ngoài các chi phí được thông báo từ bộ phận bán hàng.</p>
                            <p>– Với tất cả các điều kiện trên, quý khách hàng có yêu cầu khác, vui lòng hệ với nhân
                                viên chăm sóc khách hàng hoặc cửa hàng để được tư vấn, hỗ trợ đầy đủ nhất.</p>
                            <p>– Các chính sách và điều khoản có thể được chúng tôi thay đổi cho phù hợp nhất mà
                                không cần báo trước.</p>
                            <p><strong>CHÍNH SÁCH BẢO HÀNH</strong></p>
                            <p><strong>1. ĐIỀU KHOẢN CHUNG VỀ THỜI HẠN BẢO HÀNH.</strong></p>
                            <p>– Thời gian bảo hành các sản phẩm: 01 năm, kể từ khi nhận hàng. Nếu có trường hợp đặc
                                biệt, nhân viên Kanes sẽ thông báo đến Quý khách.</p>
                            <p>– Các sản phẩm hàng trang trí không áp dụng bảo hành nhưng áp dụng theo chính sách
                                đổi trả hàng hoá.</p>
                            <p>– Chính sách bảo hành có hiệu lực từ thời điểm bên mua nhận sản phẩm (Căn cứ theo
                                Phiếu xuất kho kiêm biên bản bàn giao &amp; phiếu bảo hành).</p>
                            <p>2<strong>. ĐIỀU KIỆN BẢO HÀNH.</strong></p>
                            <p>– Các sản phẩm vẫn nằm trong thời gian bảo hành của bên bán.</p>
                            <p>– Khách hàng cung cấp đầy đủ Phiếu xuất kho kiêm biên bản bàn giao &amp; phiếu bảo
                                hành.</p>
                            <p>– Bên bán chỉ bảo hành cho các lỗi phát sinh trong quá trình sản xuất sản phẩm.</p>
                            <p><strong>3. QUY TRÌNH BẢO HÀNH.</strong></p>
                            <p>– Cửa hàng phụ trách kiểm tra sản phẩm, xác định các trường hợp thuộc phạm vi bảo
                                hành.</p>
                            <p>– Với sản phẩm thuộc phạm vi bảo hành, cửa hàng sẽ quyết định việc sửa chữa hoặc thay
                                thế hoặc hoàn đổi lại sản phẩm.</p>
                            <p>– Đối với các sản phẩm không còn tiếp tục kinh doanh, nếu nằm trong phạm vi bảo hành,
                                không thể sửa chữa thì bên bán sẽ thay thế bằng sản phẩm có giá trị tương đồng.</p>
                            <p>– Bên bán kiểm tra sản phẩm trong vòng 48h, hoàn tất bảo hành trong vòng 48h tiếp
                                theo. Với trường hợp đặc biệt bên mua và bên bán thống nhất thời gian bảo hành.</p>
                            <p>4.<strong> PHẠM VI BẢO HÀNH.</strong></p>
                            <p>Kanes bảo hành cho các sản phẩm đáp ứng đủ hai điều kiện sau:</p>
                            <p>• Sản phẩm hư hỏng do lỗi kỹ thuật của nhà sản xuất và;</p>
                            <p>• Sản phẩm còn trong thời hạn bảo hành.</p>
                            <p>Kanes không bảo hành trong các trường hợp sau:</p>
                            <p>• Khách hàng tự ý sửa chữa khi sản phẩm gặp trục trặc.</p>
                            <p>• Sản phẩm trầy xước, hư hỏng do khách hàng không sử dụng theo đúng hướng dẫn.</p>
                            <p>• Sản phẩm được xử lý chống mọt từ bên trong, mọi tác động do môi trường bên ngoài
                                như: ẩm ướt, mối, chuột,… sẽ không được bảo hành.</p>
                            <p>• Sản phẩm bị ngập nước, gây nở, cong, vênh.</p>
                            <p>• Sản phẩm bị tác động của hơi nước, lửa,… do việc lắp đặt của các thiết bị điện nước
                                của khách hàng không đảm bảo an toàn.</p>
                            <p>• Sản phẩm không có phiếu bảo hành của công ty.</p>
                            <p>• Sản phẩm bị hao mòn tự nhiên theo tính chất của từng loại vật liệu.</p>
                            <p>• Sản phẩm không được bảo dưỡng đúng cách.</p>
                            <p>Để biết thêm thông tin, vui lòng liên hệ với cửa hàng hoặc liên hệ trực tiếp bộ phận
                                chăm sóc khách hàng:</p>
                            <p>• Hotline: 1800 7200</p>
                            <p>• Email: <a
                                    href="mailto:nhaxinhcare@akacompany.com.vn">kanescare@akacompany.com.vn</a></p>
                            <p>• Website: <a href="${pageContext.request.contextPath}/index.jsp">www.kanes.com</a></p>
                            <!-- </div> -->
                            <p><strong>CHÍNH SÁCH BẢO MẬT THANH TOÁN</strong></p>
                            <p><strong>1. CAM KẾT BẢO MẬT.</strong></p>
                            <p>Hệ thống thanh toán thẻ được cung cấp bởi các đối tác cổng thanh toán (“Đối Tác Cổng
                                Thanh Toán”) đã được cấp phép hoạt động hợp pháp tại Việt Nam. Theo đó, các tiêu
                                chuẩn bảo mật thanh toán thẻ tại Kanes đảm bảo tuân thủ theo các tiêu chuẩn bảo
                                mật ngành.</p>
                            <p><strong>2. QUY ĐỊNH BẢO MẬT.</strong></p>
                            <p>– Chính sách giao dịch thanh toán bằng thẻ quốc tế và thẻ nội địa (internet banking)
                                đảm bảo tuân thủ các tiêu chuẩn bảo mật của các Đối Tác Cổng Thanh Toán gồm:</p>
                            <ul>
                                <li>Thông tin tài chính của Khách hàng sẽ được bảo vệ trong suốt quá trình giao dịch
                                    bằng giao thức SSL (Secure Sockets Layer).
                                </li>
                                <li>Chứng nhận tiêu chuẩn bảo mật dữ liệu thông tin thanh toán (PCI DSS) do
                                    Trustwave cung cấp.
                                </li>
                                <li>Mật khẩu sử dụng một lần (OTP) được gửi qua SMS để đảm bảo việc truy cập tài
                                    khoản được xác thực.
                                </li>
                                <li>Tiêu chuẩn mã hóa MD5 128 bit.</li>
                                <li>Các nguyên tắc và quy định bảo mật thông tin trong ngành tài chính ngân hàng
                                    theo quy định của Ngân hàng nhà nước Việt Nam.
                                </li>
                            </ul>
                            <p>– Chính sách bảo mật giao dịch trong thanh toán của Kanes áp dụng với Khách hàng:
                            </p>
                            <ul>
                                <li>Kanes cung cấp tiện ích lưu giữ token – chỉ lưu chuỗi đã được mã hóa bởi Đối
                                    Tác Cổng Thanh Toán cung cấp. Kanes không trực tiếp lưu trữ thông tin thẻ
                                    khách hàng. Việc bảo mật thông tin thẻ thanh toán Khách hàng được thực hiện bởi
                                    Đối Tác Cổng Thanh Toán đã được cấp phép.
                                </li>
                                <li>Đối với thẻ quốc tế: thông tin thẻ thanh toán của Khách hàng mà có khả năng sử
                                    dụng để xác lập giao dịch không được lưu trên hệ thống của www.nhaxinh.com . Đối
                                    Tác Cổng Thanh Toán sẽ lưu trữ và bảo mật.
                                </li>
                                <li>Đối với thẻ nội địa (internet banking), Kanes chỉ lưu trữ mã đơn hàng, mã
                                    giao dịch và tên ngân hàng.
                                </li>
                            </ul>
                            <p>Kanes cam kết đảm bảo thực hiện nghiêm túc các biện pháp bảo mật cần thiết cho mọi
                                hoạt động thanh toán thực hiện trên website www.nhaxinh.com.</p>
                            <p>Tôi đã đọc và đồng ý điều kiện đổi trả hàng, giao hàng, chính sách bảo mật, điều
                                khoản dịch vụ mua hàng online.</p>
                            <p></p>
                        </div>
                    </div>
                    <form id="orderForm">
                        <label class="checkbox-label">
                            <input type="checkbox" id="terms" required>
                            Tôi đã đọc và đồng ý với điều kiện đổi trả hàng, giao hàng, chính sách bảo mật, điều khoản
                            dịch vụ mua hàng online *
                        </label>
                        <%--                        <button type="submit">Xác nhận</button>--%>

                </div>

                <button type="submit" class="order-button">ĐẶT MUA</button>
                </form>
                <script>
                    document.getElementById('orderForm').addEventListener('submit', function (event) {
                        const checkbox = document.getElementById('terms');
                        if (!checkbox.checked) {
                            event.preventDefault();
                            alert('Bạn cần đồng ý với các điều khoản để tiếp tục.');
                        }

                        alert("Đặt hàng thành công!")
                        this.submit();
                    });

                </script>
            </div>
        </div>
    </div>
</div>


<div>
    <footer class="footer">
        <div class="footer-container">
            <!-- Left Column -->
            <div class="footer-column">
                <h3>KẾT NỐI VỚI KANE'S</h3>
                <img href="${pageContext.request.contextPath}/kenes"
                     src="${pageContext.request.contextPath}/public/images/logos/logo3.png" alt=" Logo"
                     class="footer-logo">
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
                <p>Hãy để lại email của bạn để nhận được những ý tưởng trang trí mới và thông tin ưu đãi từ KANE'S
                </p>
                <p>Email: kanescare@akacompany.com.vn</p>
                <p>Hotline: 18007200</p>
                <div class="newsletter">
                    <input type="email" placeholder="Nhập email của bạn">
                    <button class="subscribe-button">ĐĂNG KÝ</button>
                </div>
            </div>
        </div>

    </footer>
</div>
<script src="${pageContext.request.contextPath}/public/js/curtainmenu.js"></script>
<script src="${pageContext.request.contextPath}/public/js/popup.js"></script>
<script>
    fetch('/locations')
        .then(response => response.json())
        .then(data => {
            const provinces = data.province;
            const districts = data.district;
            console.log("districts data:", districts)
            if (!provinces || !districts) {
                console.error('Invalid data from server.');
                return;
            }

            const provinceSelect = document.getElementById('province-select');
            const districtSelect = document.getElementById('district-select');

            districtSelect.disabled = true;

            provinces.forEach(province => {
                const option = document.createElement('option');
                option.value = province.idProvince;
                option.textContent = province.name;
                provinceSelect.appendChild(option);
            });

            provinceSelect.addEventListener('change', function () {
                const selectedProvinceId = provinceSelect.value;
                console.log("seleted provinec ID", selectedProvinceId)
                districtSelect.innerHTML = '<option disabled selected>Chọn quận / huyện</option>';
                districtSelect.disabled = !selectedProvinceId;

                if (selectedProvinceId) {
                    const filteredDistricts = districts.filter(district =>
                        district.idProvince === Number(selectedProvinceId));
                    console.log('Filtered Districts:', filteredDistricts);

                    filteredDistricts.forEach(district => {
                        const option = document.createElement('option');
                        option.value = district.idDistrict;
                        option.textContent = district.name;
                        districtSelect.appendChild(option);
                    });
                }
            });
        })
        .catch(error => console.error('Error fetching location data:', error));

</script>
<script>

</script>
</body>
</html>
