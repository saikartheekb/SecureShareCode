<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>

<#list codeList as code>

    <p>
        <span id=\"load_date\">${code.date}</span></p>
    <p>
    <pre id=\"code_snippet\"><code>${code.code}</code></pre></p>



</#list>

</body>
</html>


