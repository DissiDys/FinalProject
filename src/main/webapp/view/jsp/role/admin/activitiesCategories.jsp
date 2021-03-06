<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="customTags" %>
<html>
<fmt:setLocale value='${pageContext.request.getSession(false).getAttribute("lang")}'/>
<fmt:setBundle basename="translate" var="bundle"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="https://img.icons8.com/ios/452/clock--v1.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminAccount.css">
    <title><fmt:message key="activities_categories" bundle="${bundle}"/></title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/app/adminAccount" class="onMain"><fmt:message
            key="back" bundle="${bundle}"/></a>
</div>
<div>
    <br/>
    <br/>
    <br/>
    <form method="post" action="${pageContext.request.contextPath}/app/addCategory">
        <input required type="text" id="name" name="name" autocomplete="off">
        <input type="submit" value="<fmt:message key="add" bundle="${bundle}"/>">
    </form>
</div>
<table class="table">
    <thead>
    <tr>
        <th><fmt:message key="add" bundle="${bundle}"/></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${pageContext.request.getAttribute('categoriesList')}" var="category" varStatus="status">
        <tbody>
        <tr>
            <td>${category.name}</td>
            <td><a href="${pageContext.request.contextPath}/app/deleteCategory?id=${category.id}"><fmt:message
                    key="delete" bundle="${bundle}"/></a>
            </td>
        </tr>
        </tbody>
    </c:forEach>
</table>
    <ct:paginationTag/>
</body>
</html>