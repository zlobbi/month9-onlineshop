<#import "/spring.ftl" as spring>
<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <#if dto??>
        <h1><@spring.message "your.text"/>!</h1>
        <p>${(dto.login)!'-- missing --'} - ${(dto.email)!'-- email missing --'}</p>
        <form action="/logout" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <button type="submit" class="btn btn-outline-primary" style="width: 200px;"><@spring.message "logout.btn"/></button>
        </form>
        <div class="bottom-box mt-3">
            <a href="/" class="btn btn-outline-primary" style="width: 200px"><@spring.message "to.home"/></a>
        </div>
    </#if>

<script src="js/profile.js"></script>

</@main.renderWith>