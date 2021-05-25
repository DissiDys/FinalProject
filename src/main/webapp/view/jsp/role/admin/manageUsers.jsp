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
    <title>Title</title>
</head>
<body>
    <h2><fmt:message key="manage_users" bundle="${bundle}"/></h2>
    <table>
        <tr>
            <th>Login</th>
            <th>Password</th>
        </tr>
        <c:forEach items="${pageContext.request.getAttribute('usersList')}" var="user" varStatus="status">
            <c:if test="${user.login!='Admin'}">
                <tr>
                    <td>${user.login}<hr/></td>
                    <td>${user.password}<hr/></td>
                    <td><a href="${pageContext.request.contextPath}/app/deleteUser?id=${user.id}">delete</a><hr/></td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</body>
</html>
