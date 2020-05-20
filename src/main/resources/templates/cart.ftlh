<#import "/spring.ftl" as spring>
<#import "partials/main.ftlh" as main>

<@main.renderWith>

<div class="container">
    <h1><@spring.message "your.cart"/></h1>
    <#if cartItems??>
        <#list cartItems as item>
            <p>${item}</p>
        </#list>
        <form action="/cart/empty" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <button class="btn btn-outline-primary mb-3" style="width: 200px"><@spring.message "empty.cart"/></button>
        </form>
    <#else>
        <p><@spring.message "cart.is.empty"/></p>
    </#if>
    <form action="/cart/buy" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <button class="btn btn-outline-primary mb-3" style="width: 200px"><@spring.message "buy.btn"/></button>
    </form>

    <div>
        <a href="/" class="btn btn-outline-primary" style="width: 200px"><@spring.message "to.home"/></a>
    </div>
</div>
</@main.renderWith>