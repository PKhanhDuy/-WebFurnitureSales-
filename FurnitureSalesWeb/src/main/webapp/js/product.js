$(document).ready(function () {
    $.ajax({
        url: "/api/products",
        type: "GET",
        success: function (data) {
            let tbody = $("#product-table tbody");
            tbody.empty();
            data.forEach((product) => {
                let row = `
                    <tr>
                        <td>#${product.id}</td>
                        <td class="td-product">
                            <img src="/${product.thumb}" alt="">
                            ${product.name}
                        </td>
                        <td>${product.price.toLocaleString('vi-VN')}₫</td>
                        <td>${product.stock} Item Left
<!--                            <h4>${product.sold} Sold</h4>-->
                        </td>
                        <td>${product.category}</td>
                        <td>
                            <span class="badge rating">
                                <span class="ti-star"></span>
                                ${product.averageRating}
                            </span>
                            <h5>${product.totalReviews} reviews</h5>
                        </td>
                        <td>
                                        <a href="add-stock?id=${product.id}" class="openIframePopup">
                                            <span class="ti-plus"></span>
                                        </a>
                                        <a href="/admin/edit-product?id=${product.id}">
                                            <span class="ti-pencil-alt"></span>
                                        </a>
                                        <a href="sub-stock?id=${product.id}">
                                            <span class="ti-trash"></span>
                                        </a>
                        </td>
                    </tr>
                `;
                tbody.append(row);
            });
        },
        error: function () {
            console.error("Failed to fetch product data");
        },
    });
});

// Get Modal Elements
const iframeModal = document.getElementById('iframePopup');
// const openIframeButton = document.getElementById('openIframePopup');
const closeIframeModal = document.querySelector('.close');

const allIframeLinks = document.querySelectorAll('a[class="openIframePopup"]');
allIframeLinks.forEach(link => {
    link.addEventListener('click', () => {
        iframeModal.style.display = 'block';
    });
});

// Open Modal
// openIframeButton.addEventListener('click', () => {
//   iframeModal.style.display = 'block';
// });

// Close Modal
closeIframeModal.addEventListener('click', () => {
    iframeModal.style.display = 'none';
});

// Close Modal on Click Outside
window.addEventListener('click', (event) => {
    if (event.target === iframeModal) {
        iframeModal.style.display = 'none';
    }
});
