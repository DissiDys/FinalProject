<%@ page contentType="text/html;charset=UTF-8" %>
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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="https://img.icons8.com/ios/452/clock--v1.png" type="image/png">
    <title><fmt:message key="manage_users" bundle="${bundle}"/></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/view/jsp/role/admin/adminAccount.jsp">back</a>
<h2><fmt:message key="manage_users" bundle="${bundle}"/></h2>

<table>
    <tr>
        <th><fmt:message key="login" bundle="${bundle}"/></th>
        <th><fmt:message key="password" bundle="${bundle}"/></th>
    </tr>
    <fmt:message key="manage_users" bundle="${bundle}"/>
    <c:forEach items="${pageContext.request.getAttribute('usersList')}" var="user" varStatus="status">
        <c:if test="${user.login!='Admin'}">
            <tr>
                <td>${user.login}
                    <hr/>
                </td>
                <td>${user.password}
                    <hr/>
                </td>
                <td><a href="${pageContext.request.contextPath}/app/deleteUser?id=${user.id}"><fmt:message key="delete"
                                                                                                           bundle="${bundle}"/></a>
                    <hr/>
                </td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
