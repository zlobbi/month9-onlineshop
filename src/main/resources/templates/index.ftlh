<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Lesson 65. JPA & ORM</title>
        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <div class="container text-center">
            <h1>Содержимое страницы</h1>
            <section class="d-flex mx-auto">
                <div class="my-5 mx-auto d-flex justify-content-around">
                    <#list smartphones as s>
                        <div class="card mx-1" style="width: 18rem;">
                            <img class="card-img-top" src="../images/${s.image}" alt="${s.image}">
                            <div class="card-body text-center">
                                <h5 class="card-title">${s.name}</h5>
                                <p class="card-text">${s.description}</p>
                                <a href="#" class="btn btn-primary">Go somewhere</a>
                            </div>
                        </div>
                    </#list>
                </div>
            </section>
        </div>
    </body>
</html>
