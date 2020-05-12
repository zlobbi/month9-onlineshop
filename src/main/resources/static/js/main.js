'use strict';

async function addToCart(e) {
    e.preventDefault();
    e.stopPropagation();

    const form = document.getElementById("cart-form");
    const options = {
        method : 'post',
        body: new FormData(form)
    };

    await fetch("/cart", options);
    alert("обновите страницу для просмотра данных из сессии, или перейдите в корзину");
}

const updateElement = document.getElementById("cart-add");
updateElement.addEventListener('click', addToCart);