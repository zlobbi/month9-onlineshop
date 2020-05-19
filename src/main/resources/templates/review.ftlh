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
    <h1>Feedback</h1>
    <form action="/cart/feedback" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <textarea placeholder="Enter the text of feedback"></textarea>
        <button>send</button>
    </form>

    <div>
        <a href="/">to homepage</a>
    </div>
</div>

</body>
</html>