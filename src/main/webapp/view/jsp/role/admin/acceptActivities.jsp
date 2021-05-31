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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="https://img.icons8.com/ios/452/clock--v1.png" type="image/png">
    <title><fmt:message key="activities" bundle="${bundle}"/></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/view/jsp/role/admin/adminAccount.jsp">back</a>
<h2><fmt:message key="accept" bundle="${bundle}"/></h2>

<table>
    <tr>
        <th><fmt:message key="login" bundle="${bundle}"/></th>
        <th><fmt:message key="activities" bundle="${bundle}"/></th>
    </tr>
    <c:forEach items="${pageContext.request.getAttribute('usersList')}" var="user" varStatus="status">
        <c:forEach items="${pageContext.request.getAttribute('activitiesList'.concat(user.login))}" var="activity" varStatus="status">
            <tr>
                <td>${user.login}
                    <hr/>
                </td>
                <td>${activity.name}, ${activity.category.name}
                     <hr/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/app/acceptActivityForUser?user_id=${user.id}&activity_id=${activity.id}&accept=true"><fmt:message
                            key="accept" bundle="${bundle}"/></a>
                    <hr/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/app/acceptActivityForUser?user_id=${user.id}&activity_id=${activity.id}&accept=false"><fmt:message
                            key="delete" bundle="${bundle}"/></a>
                    <hr/>
                </td>
            </tr>
        </c:forEach>
    </c:forEach>
</table>
</body>
</html>