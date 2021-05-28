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
    <title><fmt:message key="activities" bundle="${bundle}"/></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/view/jsp/role/admin/adminAccount.jsp">back</a>
<h2><fmt:message key="activities" bundle="${bundle}"/></h2>

<table>
    <tr>
        <th><fmt:message key="name" bundle="${bundle}"/></th>
        <th><fmt:message key="category" bundle="${bundle}"/></th>
    </tr>
    <c:forEach items="${pageContext.request.getAttribute('activityList')}" var="activity" varStatus="status">
        <tr>
            <td>${activity.name}
                <hr/>
            </td>
            <td>${activity.category.name}
                <hr/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>