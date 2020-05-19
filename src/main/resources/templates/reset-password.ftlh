<#import "partials/main.ftlh" as main>
<@main.renderWith>
    <section id="page-splash" class="page-splash mt-5">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                <h3>Reset password</h3>
                <form method="post" action="/reset-password" id="">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <#if reset??>
                        <p >Checking token: <span style="color:red">${reset.token}</span></p>
                    </#if>
                    <input type="text" name="token" class="form-control mb-1" autofocus
                           placeholder="enter checking token"><br>
                    <input type="text" name="password" class="form-control mb-1"
                           placeholder="new password"><br>
                    <button class="btn btn-outline-primary btn-block" type="submit">reset</button>
                    <br>
                </form>
            </div>
        </div>
    </section>
    <script src="js/login.js"></script>
</@main.renderWith>
