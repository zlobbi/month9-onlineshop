<#import "partials/main.ftlh" as main>

<@main.renderWith>
    <div class="container">
        <h1>Feedback</h1>
        <form action="/cart/feedback" class="row justify-content-center" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <div class="input-group">
                <textarea class="form-control mx-auto" style="max-width: 500px;" placeholder="leave feedback" autofocus></textarea>
            </div>
            <button class="btn btn-outline-primary my-3" style="width: 200px">send</button>
        </form>

        <div>
            <a href="/" class="btn btn-outline-primary mb-3" style="width: 200px">to homepage</a>
        </div>
    </div>
</@main.renderWith>

