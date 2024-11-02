<!DOCTYPE html>
<head>
    <title>scc主页</title>
</head>
<body>
<h1>欢迎来到scc的个人主页</h1>
<ul>
    <#--循环渲染导航菜单-->
    <#list menuItems as item>
        <li><a href="${item.url}">${item.label}</a></li>
    </#list>
</ul>
<footer>
    ${currentYear} scc主页，All rights reserved
</footer>
</body>
</html>