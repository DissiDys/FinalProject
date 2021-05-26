<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value='${pageContext.request.getSession(false).getAttribute("lang")}'/>
<fmt:setBundle basename="translate" var="bundle"/>

<c:url value="" var="EnLang">
    <c:param name="lang" value="en"/>
</c:url>

<c:url value="" var="UkrLang">
    <c:param name="lang" value="ukr"/>
</c:url>
<head>
    <title><fmt:message key="activities_categories" bundle="${bundle}"/></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/view/jsp/role/admin/adminAccount.jsp">back</a>
<h2><fmt:message key="activities_categories" bundle="${bundle}"/></h2>
<form method="post" action="${pageContext.request.contextPath}/app/addCategory">
    <input required type="text" id="name" name="name" autocomplete="off">
    <input type="submit" value="<fmt:message key="add" bundle="${bundle}"/>">
</form>
<table>
    <tr>
        <th><fmt:message key="add" bundle="${bundle}"/></th>
    </tr>
    <c:forEach items="${pageContext.request.getAttribute('categoriesList')}" var="category" varStatus="status">
        <tr>
            <td>${category.name}
                <hr/>
            </td>
            <td><a href="${pageContext.request.contextPath}/app/deleteCategory?id=${category.id}"><fmt:message
                    key="delete" bundle="${bundle}"/></a>
                <hr/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>