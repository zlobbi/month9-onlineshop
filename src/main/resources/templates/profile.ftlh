<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <#if dto??>
        <h1>Ваши учетные данные!</h1>
        <p>${(dto.login)!'-- missing --'} - ${(dto.email)!'-- email missing --'}</p>
        <form action="/logout" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <button type="submit" class="btn btn-outline-primary" style="width: 200px;">Выйти</button>
        </form>
    <#else>
        <h1>Пользователь не найден!</h1>
    </#if>
    <div class="bottom-box">
        <a href="/">На главную страницу</a>
    </div>

<script src="js/profile.js"></script>

</@main.renderWith>