document.addEventListener("DOMContentLoaded", function () {
    addCart()
    favoriteAdd()
    function addCart() {
        document.querySelectorAll(".add-to-cart").forEach((button) => {
            button.addEventListener("click", function () {
                const productElement = this.closest(".product");
                const productId = productElement.dataset.id;
                const productName = productElement.dataset.name;
                const productImg = productElement.dataset.img;
                const productPrice = parseFloat(productElement.dataset.price);
                console.log(productId)
                // Gửi yêu cầu AJAX tới servlet
                fetch("/CartController", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        id: productId,
                        name: productName,
                        img: productImg,
                        price: productPrice,
                        quantity: 1
                    }),
                })
                    .then((response) => response.json())
                    .then((cart) => {
                        console.log(cart.userName)
                        if (cart.userName != null) {
                            updateCartUI(cart);
                            showNotification("Đã thêm sản phẩm vào giỏ hàng.")
                        } else {
                            loginNotification("Bạn chưa đăng nhập!");
                        }
                    })
                    .catch((error) => console.error("Lỗi:", error));
            });
        });
    }

    function removeItem() {
        document.querySelectorAll(".remove-item").forEach((button) => {
            button.addEventListener("click", function () {
                const productId = this.dataset.id;
                console.log(productId)
                fetch("/CartController?action=remove", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({id: productId})
                })
                    .then((response) => response.json())
                    .then((cart) => {
                        if (cart.userName != null) {
                            updateCartUI(cart)
                            showNotification("Đã xóa sản phẩm khỏi giỏ hàng.")
                        } else {
                            loginNotification("Phiên đã hết. Vui lòng đăng nhập lại.")
                        }
                    })
                    .catch((error) => console.error("Lỗi: ", error))
            });
        });
    }

    plusQuantityProduct()
    minusQuantityProduct()
    removeItem();

    function updateCartUI(cart) {
        // Lấy container hiển thị giỏ hàng
        const cartContainer = document.getElementById('list-product-cart');
        const totalCart = document.querySelectorAll('.total-cart')
        const countProduct = document.querySelectorAll('.total-count')

        // Xóa nội dung cũ của giỏ hàng
        cartContainer.innerHTML = '';

        //định dạng tiền tệ
        const formatter = new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND',
        });
        totalCart.forEach(item => {
                    item.textContent = formatter.format(cart.totalPrice)
        })

        // Duyệt qua từng sản phẩm trong giỏ hàng (cart là một mảng JSON)
        cart.lists.forEach((item) => {

            // Định dạng giá trị
            const formattedPrice = formatter.format(item.price);

            // Tạo HTML cho từng sản phẩm
            const cartItem = document.createElement('div');
            cartItem.classList.add('row');
            cartItem.innerHTML = `        
                    <div class="col-md-12 col-12 order">
                        <div class="image center-items">
                            <img src="${item.img}" alt="">
                        </div>
                        <div class="detail-order center-items" style="justify-content: left;">
                            <div>
                                <h6>${item.name}</h6>
                                <span>${item.quantity}</span> x <span> ${formattedPrice}</span>
                            </div>
                        </div>
                        <div class="close-orders center-items">
                                <button class="remove-item" data-id="${item.id}" style="border: none; background-color: white">
                                    <i class="bi bi-x-circle"></i>
                                </button>
                        </div>
                    </div>
            `;
            // Thêm sản phẩm vào container
            cartContainer.appendChild(cartItem);
        });

        const productsOfCart = document.getElementById('products-of-cart')
        if (productsOfCart != null) {
            productsOfCart.innerHTML = '';
            cart.lists.forEach((item) => {
                // Định dạng giá trị
                const formattedPrice = formatter.format(item.price);

                // Tạo HTML cho từng sản phẩm
                const cartItem = document.createElement('div');
                cartItem.classList.add('cart-item');
                cartItem.innerHTML = `
                     <div class="image-box">
                            <img src="${item.img}" alt="Armchair Mây mới"
                                 class="item-image">
                        </div>
                        <div class="item-details">
                            <h3>${item.name}</h3>
                            <p class="item-price">${formattedPrice}</p>
                            <p class="wishlist"><span><i class="bi bi-heart"></i></span> Thêm vào Wishlist</p>
                        </div>
                       <div class="item-actions item-product" data-id="${item.id}" data-name="${item.name}" data-img="${item.img}" data-price="${item.price}" >
                            <button class="remove-item" data-id="${item.id}" style="border: none; background-color: white;">
                                <i class="bi bi-x-circle"></i>
                            </button>
                            <div class="quantity buttons-added left-btn">
                                <input type="button" value="-" class="minus-btn" id="button-minus-quantity">
                                <input type="number" name="quatity" class="quantity-input" id="input-quantity" value="${item.quantity}" min="1"
                                       inputmode="numeric" autocomplete="off">
                                <input type="button" value="+" class="plus-btn" id="button-plus-quantity">
                            </div>
                        </div>
            `;
                // Thêm sản phẩm vào container
                productsOfCart.appendChild(cartItem);
            })
        }


        removeItem();
        plusQuantityProduct()
        minusQuantityProduct()

        if (countProduct != null) {
            countProduct.forEach(item => {
                item.textContent = cart.lists.length;
            });
        }
        favoriteAdd()
        fetchFavorites()
    }

    // 8.3.6. decreaseQuantity() -người dùng muốn giảm số lượng-
    function plusQuantityProduct() {
        document.querySelectorAll(".plus-btn").forEach((btn) => {
            btn.addEventListener("click", function () {
                const itemProduct = this.closest(".item-product");
                const productId = itemProduct.dataset.id;
                const productName = itemProduct.dataset.name;
                const productImg = itemProduct.dataset.img;
                const productPrice = parseFloat(itemProduct.dataset.price);
                // const productQuantity = productElement.dataset.quantity;

                // Gửi yêu cầu AJAX tới servlet
                fetch("/CartController", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        id: productId,
                        name: productName,
                        img: productImg,
                        price: productPrice,
                        quantity: 1
                    }),
                })
                    .then((response) => response.json())
                    .then((cart) => {
                        console.log(cart.userName)
                        if (cart.userName != null) {
                            updateCartUI(cart);
                        }
                    })
                    .catch((error) => console.error("Lỗi:", error));
            })
        })
    }

    // 8.3.6. increaseQuantity() -người dùng muốn tăng số lương-
    function minusQuantityProduct() {
        document.querySelectorAll(".minus-btn").forEach((btn) => {
            btn.addEventListener("click", function () {
                const itemProduct = this.closest(".item-product");
                const productId = itemProduct.dataset.id;
                const productName = itemProduct.dataset.name;
                const productImg = itemProduct.dataset.img;
                const productPrice = parseFloat(itemProduct.dataset.price);
                // const productQuantity = productElement.dataset.quantity;

                // Gửi yêu cầu AJAX tới servlet
                fetch("/CartController?action=plus", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        id: productId,
                        name: productName,
                        img: productImg,
                        price: productPrice,
                        quantity: 1
                    }),
                })
                    .then((response) => response.json())
                    .then((cart) => {
                        console.log(cart.userName)
                        if (cart.userName != null) {
                            updateCartUI(cart);
                        }
                    })
                    .catch((error) => console.error("Lỗi:", error));
            })
        })
    }


    function showNotification(message) {
        const notification = document.getElementById('notification');

        // Thay đổi nội dung thông báo nếu cần
        notification.textContent = message;

        // Hiển thị thông báo
        notification.classList.remove('hidden');

        // Tự động ẩn thông báo sau 3 giây
        setTimeout(() => {
            notification.classList.add('hidden');
        }, 1000); // 3000ms = 3 giây
    }

    function loginNotification(message) {
        const notification = document.getElementById('login-notification');

        // Thay đổi nội dung thông báo nếu cần
        notification.textContent = message;

        // Hiển thị thông báo
        notification.classList.remove('hidden');

        // Tự động ẩn thông báo sau 3 giây
        setTimeout(() => {
            notification.classList.add('hidden');
        }, 500); // 3000ms = 3 giây
    }

//thêm sản phẩm yêu thích
    function favoriteAdd() {
        document.querySelectorAll(".wishlist-button").forEach((button) => {
            button.addEventListener("click", function () {
                // button.classList.toggle("clicked")

                const productElement = this.closest(".favourite-product");

                const productId = parseInt(productElement.dataset.id);
                const userId = parseInt(productElement.dataset.user);
                console.log(userId)
                fetch("/favouriteProducts", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        productID: productId,
                        userID: userId
                    }),
                })
                    .then((response) => response.json())
                    .then(
                        (list) => {
                            button.classList.toggle("clicked")
                        }
                    )
                    .catch((error) => console.error("Lỗi:", error));
            })
        })
    }


//Lọc sản phẩm
    document.querySelectorAll(".filter").forEach(button => {
        button.addEventListener("click", function () {
            const sort = document.getElementById("sort-filter").textContent
            const material = document.getElementById("material-filter").textContent
            console.log(sort, material)
            fetch("/FilterProduct", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json; charset=UTF-8",
                },
                body: JSON.stringify({
                    sort: sort,
                    material: material
                }),
            })
                .then((response) => {
                    // response.json()
                    return response.json()
                })
                .then(data => {
                    updateUI(data)
                    console.log(data)
                })
                .catch((error) => console.error("Lỗi:", error));
        })
    })


    function updateUI(result) {
        //định dạng tiền tệ
        const formatter = new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND',
        });
        const productArea = document.getElementById('product-area')
        productArea.innerHTML = ''
        result.pageProducts.forEach((item) => {
            const column = document.createElement('div')
            const formattedPrice = formatter.format(item.price);
            column.classList.add("col-md-3")
            column.innerHTML = `
                <div class="card product-card product" data-id="${item.id}" data-name="${item.proName}"
                     data-img="${item.thumb}" data-price="${item.price}">
                    <a href="product?id=${item.id}&atributeID=${item.atributeID}&cateID=${item.cateID}">
                        <img src="${item.thumb}" class="image-top"
                             alt="${item.proName}">
                        <img src="${item.thumb}" class="image-back"
                             alt="${item.proName}">
                    </a>
                    <div class="card-body">
                        <h6 class="product-name">${item.proName}</h6>
                        <div class="like-price-product favourite-product" data-id="${item.id}"
                             data-user="${result.userId}">
                        <span class="product-price">${formattedPrice}</span>
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
                                    <a href="javascript:void(0);"
                                               onclick="showProductDetails(${item.id}, ${item.atributeID}, ${item.cateID})">
                                                <p>XEM THÊM</p></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        `
            productArea.appendChild(column);
        })
        const pagination = document.getElementById("p-pagination")
        pagination.innerHTML = ''

        const currentPage = result.currentPage;
        const totalPages = result.totalPages;
        let nextPage = 0;
        let previousPage = 0;
        if (currentPage > 1) {
            previousPage = currentPage - 1;
        }
        if (currentPage < totalPages) {
            nextPage = currentPage + 1;
        }
        const newPaginationBox = document.createElement('div')
        newPaginationBox.classList.add("p-pagination-box")

        let paginationHTML = `
             <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center"
                        style="--bs-pagination-focus-box-shadow: 0 0 0 0.25rem rgba(21, 21, 22, 0.25);">
                            <li class="page-item ${currentPage === 1 ? 'disabled' : ''}">
                                    <button class="page-link" id="previous-btn">« Trước</button>
                            </li>
            `
        for (let page = 1; page <= totalPages; page++) {
            paginationHTML += `
                    <li class="page-item ${page == currentPage ? 'active' : ''}">
                         <button class="page-link" id="current-btn">${page}</button>
                    </li>
            `
        }
        paginationHTML += `
                <li class="page-item ${currentPage === totalPages ? 'disabled' : ''}"">
                            <span aria-hidden="true"> <button class="page-link" id="next-btn">Tiếp »</button></span>
                </li>
            </ul>
        </nav>
        `
        newPaginationBox.innerHTML = paginationHTML
        pagination.appendChild(newPaginationBox)


        document.getElementById("next-btn").addEventListener("click", function () {
            const sort = document.getElementById("sort-filter").textContent
            const material = document.getElementById("material-filter").textContent
            console.log("TTTTTT")
            fetch(`/FilterProduct?page=${currentPage + 1}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json; charset=UTF-8",
                },
                body: JSON.stringify({
                    sort: sort,
                    material: material
                }),
            })
                .then((response) => {
                    // response.json()
                    return response.json()
                })
                .then(data => {
                    updateUI(data)
                    console.log(data)
                })
                .catch((error) => console.error("Lỗi:", error));
        })

        document.getElementById("previous-btn").addEventListener("click", function () {
            const sort = document.getElementById("sort-filter").textContent
            const material = document.getElementById("material-filter").textContent
            fetch(`/FilterProduct?page=${currentPage - 1}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json; charset=UTF-8",
                },
                body: JSON.stringify({
                    sort: sort,
                    material: material
                }),
            })
                .then((response) => {
                    // response.json()
                    return response.json()
                })
                .then(data => {
                    updateUI(data)
                    console.log(data)
                })
                .catch((error) => console.error("Lỗi:", error));

        })
        addCart()
        favoriteAdd()
    }
    fetchFavorites()
});

function fetchFavorites() {
    fetch("/favouriteProducts")
        .then(response => response.json())
        .then(favorites => {
            updateFavoriteDisplay(favorites)
        })
        .catch(error => {
            console.error('Error fetching favorites:', error)
        })
}

function updateFavoriteDisplay(favoritesList) {
    document.querySelectorAll(".favourite-product").forEach((favorites) => {
        const productId = parseInt(favorites.dataset.id)
        const heartIcon = favorites.querySelector(".wishlist-button")
        if (favoritesList.includes(productId)) {
            heartIcon.classList.add("clicked")
        } else {
            heartIcon.classList.remove("clicked")

        }
    })
}


