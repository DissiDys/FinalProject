<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value='${pageContext.request.getSession(false).getAttribute("lang")}'/>
<fmt:setBundle basename="translate" var="bundle"/>

<c:url value="/app/unconfirmedActivitiesList" var="EnLang">
    <c:param name="lang" value="en"/>
</c:url>

<c:url value="/app/unconfirmedActivitiesList" var="UkrLang">
    <c:param name="lang" value="ukr"/>
</c:url>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="https://img.icons8.com/ios/452/clock--v1.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminAccount.css">
    <title><fmt:message key="activities" bundle="${bundle}"/></title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/view/jsp/role/admin/adminAccount.jsp" class="onMain"><fmt:message key="back" bundle="${bundle}"/></a>
</div>
<br/>
<br/>
<br/>
<br/>
<br/>
<table class="table">
    <thead>
    <tr>
        <th><fmt:message key="operation" bundle="${bundle}"/></th>
        <th><fmt:message key="login" bundle="${bundle}"/></th>
        <th><fmt:message key="activities" bundle="${bundle}"/></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${pageContext.request.getAttribute('usersList')}" var="user" varStatus="status">
        <c:forEach items="${pageContext.request.getAttribute('activitiesList'.concat(user.login))}" var="activity"
                   varStatus="status">
            <c:set value="${pageContext.request.getAttribute('operation'.concat(user.login).concat(activity.name))}"
                   var="operation"/>
            <tbody>
            <tr>
                <td>${operation}</td>
                <td>${user.login}</td>
                <td>${activity.name}, ${activity.category.name}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/app/acceptActivityForUser?user_id=${user.id}&activity_id=${activity.id}&accept=true"><fmt:message
                            key="accept" bundle="${bundle}"/></a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/app/acceptActivityForUser?user_id=${user.id}&activity_id=${activity.id}&accept=false"><fmt:message
                            key="dont_accept" bundle="${bundle}"/></a>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </c:forEach>
</table>
</body>
</html>