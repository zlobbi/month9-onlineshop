<div>
    <#if hasPrev>
        <a id="loadPrev" href="${prevPageLink!''}">&lt;&lt;&lt;Previous page</a>
    </#if>

    <#if hasNext>
        <a id="loadNext" href="${nextPageLink!''}" data-default-page-size="${defaultPageSize}">Next page&gt;&gt;&gt;</a>
    </#if>
</div>