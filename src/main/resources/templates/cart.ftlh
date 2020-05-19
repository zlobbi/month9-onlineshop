<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Lesson 69. Storing data in session</title>

    <link rel="stylesheet" href="/css/main.css">
</head>
<body>

<div class="container">
    <h1>Ваша корзина</h1>
    <#if cartItems??>
        <#list cartItems as item>
            <p>${item}</p>
        </#list>
        <form action="/cart/empty" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <button>Очистить корзину</button>
        </form>
    <#else>
        <p>тут ничего нет!</p>
    </#if>
    <form action="/cart/buy" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <button>Buy</button>
    </form>

    <div>
        <a href="/">назад</a>
    </div>
</div>

</body>
</html>