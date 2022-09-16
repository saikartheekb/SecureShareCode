<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>

    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<span th:text="${date}" id="load_date">${date}</span>
<pre id="code_snippet"><span th:text="${code}"><code>${code}</span></code></pre>
<#if views_restriction?has_content>
<p> <span id="views_restriction"> ${views_restriction} </span>
    </#if>
    <#if time_restriction?has_content>
<p> <span id="time_restriction"> ${time_restriction} </span>
    </#if>
</body>
</html>
