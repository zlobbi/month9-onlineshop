<#import "partials/main.ftlh" as main/>

<@main.renderWith>

    <div class="container text-center">
        <section class="d-flex mx-auto">
            <div class="my-5 mx-auto d-flex justify-content-around">
                <#list smartphones as s>
                    <div class="card mx-1" style="">
                        <img class="card-img-top " style="height: 200px; width: auto" src="../images/${s.image}"
                             alt="${s.image}">
                        <div class="card-body text-center">
                            <h5 class="card-title">${s.name}</h5>
                            <p class="card-text">${s.description}</p>
                            <p class="card-text">${s.price} $</p>
                            <a href="#" class="btn btn-primary" style="width: 120px">info</a>
                        </div>
                    </div>
                </#list>
            </div>
        </section>
        <#include "partials/pagination.ftlh">
    </div>
    <script src="/js/index.js"></script>
</@main.renderWith>
