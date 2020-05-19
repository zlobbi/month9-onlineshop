<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <section id="page-splash" class="page-splash mt-5">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <h3>Login</h3>
                <form method="post" action="/login" id="login-form">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <#if error!false >
                        <p style="color:red">User not found!</p>
                    </#if>
                    <input type="email" name="username" class="form-control mb-1" value="${(dto.email)!''}" autofocus
                           placeholder="email"><br>
                    <input type="password" name="password" class="form-control mb-1" value=""
                           placeholder="password"><br>
                    <button class="btn btn-outline-primary btn-block" type="submit">sign in</button>
                    <br>
                    <a href="/register" class="btn btn-outline-primary btn-block" >registration</a>
                    <br>
                    <a href="/forgot-password" class="btn btn-outline-primary btn-block">forgot password</a>
                </form>
            </div>
        </div>
    </section>
    <script src="js/login.js"></script>
</@main.renderWith>
