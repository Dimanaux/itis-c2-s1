<#include "header.ftl">

<#macro title></#macro>
<#macro imports></#macro>

<#macro content></#macro>

<#macro page>
<!DOCTYPE html>
<html>
<head>
    <@title></@title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <link href="./static/css/about.css" rel="stylesheet">
    <script type="text/javascript" src="./static/js/goto.js" async></script>
    <@imports></@imports>
</head>
<body>
    <@header></@header>
    <main>
        <@content></@content>
    </main>
</body>
</html>
</#macro>



