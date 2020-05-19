<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <section id="page-splash" class="page-splash mt-5">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <h3>Change password for</h3>
                <form method="post" action="/forgot-password" id="login-form">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <#if error??>
                        <p style="color:red">${error}</p>
                    </#if>
                    <input type="email" name="email" class="form-control mb-1" autofocus
                           placeholder="email"><br>
                    <button class="btn btn-outline-primary btn-block" type="submit">send</button>
                    <br>
                </form>
            </div>
        </div>
    </section>
    <script src="js/login.js"></script>
</@main.renderWith>
