<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>${pageContext.request.getAttribute("msg")}</h1>
<br/>
<a href="${pageContext.request.contextPath}/app/logout">На головну</a>
</body>
</html>
