<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Lesson 65. JPA & ORM</title>
    </head>
    <body>
        <h1>Содержимое страницы</h1>

        <ul>
        <#list brands as b>
            <li>
                <p>${b.id} - ${b.name} - ${b.icon}</p>
            </li>
        </#list>
        </ul>

    </body>
</html>
