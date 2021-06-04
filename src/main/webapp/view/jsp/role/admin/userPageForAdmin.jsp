<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value='${pageContext.request.getSession(false).getAttribute("lang")}'/>
<fmt:setBundle basename="translate" var="bundle"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="https://img.icons8.com/ios/452/clock--v1.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/adminAccount.css">
    <title><fmt:message key="manage_users" bundle="${bundle}"/></title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/app/usersList" class="onMain"><fmt:message
            key="back" bundle="${bundle}"/></a>
</div>
<br/>
<br/>
<br/>
<table class="table">
    <thead>
    <tr>
        <th><fmt:message key="name" bundle="${bundle}"/></th>
        <th><fmt:message key="category" bundle="${bundle}"/></th>
        <th><fmt:message key="time_spent" bundle="${bundle}"/></th>
    </tr>
    </thead>
    <c:forEach items="${pageContext.request.getAttribute('activitiesList')}" var="activity" varStatus="status">
        <c:set value="${pageContext.request.getAttribute('hoursSpent'.concat(activity.name))}" var="hours"/>
        <c:set value="${pageContext.request.getAttribute('minutesSpent'.concat(activity.name))}" var="minutes"/>
        <tbody>
        <td>${activity.name}</td>
        <td>${activity.category.name}</td>
        <td>${hours} <fmt:message key="hours" bundle="${bundle}"/> ${minutes} <fmt:message key="minutes"
                                                                                           bundle="${bundle}"/>
        </td>
        </tr>
        </tbody>
        <c:if test="${pageContext.request.getAttribute('msg'.concat(activity.name))}">
            <label style="color: green"><fmt:message key="request_to_admin_sended" bundle="${bundle}"/></label>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
