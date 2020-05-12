<#import "partials/main.ftlh" as main/>

<@main.renderWith>
    <h1>Данные из сессии</h1>
    <div class="border"><strong><span class="key-cell">ключ</span><span class="value-cell">значение</span></strong></div>
    <#list sessionAttributes as key, value>
        <div class="border"><span class="key-cell">${key}</span><span class="value-cell">${value}</span><br></div>
    </#list>
    <form action="/cart/add" method="post" id="cart-form">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <h2>Добавить в корзину.</h2>
        <div>
            <label for="value">что покупаем? </label>
            <input name="value" id="value">
            <button type="submit">Добавить в корзину</button>
        </div>
        <div>
            <div class="key-cell"><a href="" id="cart-add" >Добавить через fetch()</a>(обновите страницу)</div>
            <a href="/cart" class="value-cell">Посмотреть корзину</a>
        </div>
    </form>
    <form action="/store" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <h2>Сохраним что-то в сессии.</h2>
        <div>
            <label for="key">имя поля: </label>
            <input name="key" id="key">
            <label for="value">некоторое значение: </label>
            <input name="value" id="value">
            <button type="submit">Добавить</button>
        </div>
    </form>
    <div>
        <h2><a href="/invalidate">Удалим всё, включая текущую сессию.</a></h2>
    </div>
    <div class="container text-center">
        <section class="d-flex mx-auto">
            <div class="my-5 mx-auto d-flex justify-content-around">
                <#list smartphones as s>
                    <div class="card mx-1" style="">
                        <img class="card-img-top " style="height: 200px; width: auto" src="../images/${s.image}"
                             alt="${s.image}">
                        <div class="card-body text-center">
                            <h5 class="card-title">${s.name}</h5>
                            <p class="card-text">${s.description}</p>
                            <p class="card-text">${s.price} $</p>
                            <a href="#" class="btn btn-primary" style="width: 120px">info</a>
                        </div>
                    </div>
                </#list>
            </div>
        </section>
        <#include "partials/pagination.ftlh">
    </div>
    <script src="/js/main.js"></script>
</@main.renderWith>
