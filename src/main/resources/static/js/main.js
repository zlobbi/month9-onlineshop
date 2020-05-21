'use strict';

async function addToCart(e) {
    e.preventDefault();
    e.stopPropagation();

    const form = document.getElementById("adding");
    const options = {
        method : 'post',
        body: new FormData(form)
    };

    await fetch("/cart", options);
    alert("обновите страницу для просмотра данных из сессии, или перейдите в корзину");
}

if (document.getElementById("cart-add") != null ) {
    const updateElement = document.getElementById("cart-add");
    updateElement.addEventListener('click', addToCart);
}
