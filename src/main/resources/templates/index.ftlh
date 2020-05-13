<#import "partials/main.ftlh" as main/>

<@main.renderWith>
    <div class="container text-center">
        <section class="d-flex mx-auto">
            <#if cart??>
                <a href="/cart">cart: ${cart}</a>
            </#if>
            <div class="my-5 mx-auto d-flex justify-content-around">
                <#list smartphones as s>
                    <div class="card mx-1" style="">
                        <img class="card-img-top " style="height: 200px; width: auto" src="../images/${s.image}"
                             alt="${s.image}">
                        <div class="card-body text-center">
                            <h5 class="card-title">${s.name}</h5>
                            <p class="card-text">${s.description}</p>
                            <p class="card-text">${s.price} $</p>
                            <form id="adding" action="/cart/add" method="post">
                                <#if _csrf??>
                                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                                </#if>
                                <input name="value" value="${s.id}" hidden>
                                <button type="submit">add to cart</button>
                            </form>
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
