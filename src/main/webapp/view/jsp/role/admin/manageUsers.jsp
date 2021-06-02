<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value='${pageContext.request.getSession(false).getAttribute("lang")}'/>
<fmt:setBundle basename="translate" var="bundle"/>

<c:url value="/app/usersList" var="EnLang">
    <c:param name="lang" value="en"/>
</c:url>

<c:url value="/app/usersList" var="UkrLang">
    <c:param name="lang" value="ukr"/>
</c:url>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="https://img.icons8.com/ios/452/clock--v1.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminAccount.css">
    <title><fmt:message key="manage_users" bundle="${bundle}"/></title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/view/jsp/role/admin/adminAccount.jsp" class="onMain"><fmt:message
            key="back" bundle="${bundle}"/></a>
    <a href="${EnLang}" class="localizationEN">EN</a>
    <a href="${UkrLang}" class="localizationUA">UKR</a>
</div>
<br/>
<br/>
<br/>
<table class="table">
    <thead>
    <tr>
        <th><fmt:message key="login" bundle="${bundle}"/></th>
        <th><fmt:message key="password" bundle="${bundle}"/></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${pageContext.request.getAttribute('usersList')}" var="user" varStatus="status">
        <c:if test="${user.login!='Admin'}">
            <tbody>
            <tr>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td><a href="${pageContext.request.contextPath}/app/deleteUser?id=${user.id}"><fmt:message key="delete"
                                                                                                           bundle="${bundle}"/></a>
                </td>
            </tr>
            </tbody>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
