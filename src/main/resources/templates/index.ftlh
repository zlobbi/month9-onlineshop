<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Lesson 65. JPA & ORM</title>
    </head>
    <body>
        <h1>Содержимое страницы</h1>

        <ul>
        <#list products as ft>
            <li>
                <p>${ft.id} - ${ft.name} - ${ft.image} - ${ft.type.name}</p>
            </li>
        </#list>
        </ul>

    </body>
</html>
