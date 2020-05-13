<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <div class="text-center">
        <div class="d-flex mx-auto">
            <form id="search-form" method="get" class="mx-auto d-flex">
                <div class="form-row align-items center">
                    <div class="form-group col-auto my-1">
                        <input type="text" name="text" class="form-control" value="${(form.text)!''}" id="input"
                               placeholder="search">
                    </div>
                    <div class="form-group col-auto my-1">
                        <select id="param" typeof="text" name="param" class="form-control">
                            <option value="by name" selected>by name</option>
                            <option value="by price">by price</option>
                            <option value="by brand">by brand</option>
                            <option value="in description">in description</option>
                        </select>

                    </div>
                    <button type="submit" formaction="/search" class="btn btn-primary">search</button>
                </div>
            </form>
        </div>

        <#if smartphones?? >
            <section class="d-flex">
                <div class="my-5 mx-auto d-flex justify-content-around">
                    <#list smartphones as s>
                        <div class="card mx-1" style="">
                            <img class="card-img-top " style="height: 200px; width: auto" src="../images/${s.image}"
                                 alt="${s.image}">
                            <div class="card-body text-center">
                                <h5 class="card-title">${s.name}</h5>
                                <p class="card-text">${s.description}</p>
                                <p class="card-text">${s.price} $</p>
                                <form id="adding" action="/cart/add" method="post">
                                    <#if _csrf??>
                                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                                    </#if>
                                    <input name="value" value="${s.id}" hidden>
                                    <button type="submit">add to cart</button>
                                </form>
                                <a href="#" class="btn btn-primary" style="width: 120px">info</a>
                            </div>
                        </div>
                    </#list>
                </div>
            </section>
            <#include "partials/pagination.ftlh">
        </#if>
    </div>

</@main.renderWith>