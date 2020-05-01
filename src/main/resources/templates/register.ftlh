<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lesson 60. Javascript 101</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<section id="page-splash" class="page-splash">
    <div class="d-flex justify-content-center">
        <div class="mx-3">
            <h3>Registration</h3>
            <form id="registration-form" method="post">
                <input type="email" name="email" class="form-control mb-1" value="${form.email!''}" placeholder="Email"><br>
                <input type="text" name="login" class="form-control mb-1" value="${form.login!''}" placeholder="Login"><br>
                <input type="password"  name="password" class="form-control mb-1" value="${form.password!''}" placeholder="Password"><br>
                <button class="btn btn-outline-primary btn-block" type="submit">Register</button><br>
                <#if errors?? >
                    <p>Invalid fields!</p>
                    <#list errors as fieldError>
                        <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                    </#list>
                </#if>
                <#if user?? >
                    <p>${user.login} - ${user.email} already registered!</p>
                </#if>
            </form>
        </div>
    </div>
</section>
</body>
</html>

<script src="js/registration.js"></script>