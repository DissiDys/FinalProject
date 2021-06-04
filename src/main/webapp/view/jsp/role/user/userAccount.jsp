<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value='${pageContext.request.getSession(false).getAttribute("lang")}'/>
<fmt:setBundle basename="translate" var="bundle"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/userAccount.css">
    <link rel="shortcut icon" href="https://img.icons8.com/ios/452/clock--v1.png" type="image/png">
    <title><fmt:message key="activities" bundle="${bundle}"/></title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/app/logout" class="logout"><fmt:message key="logout"
                                                                                        bundle="${bundle}"/></a>
</div>
<div>
    <br/>
    <br/>
    <br/>
    <form method="post" action="${pageContext.request.contextPath}/app/sendAddActivityRequestToAdmin">
        <select name="activity_id">
            <c:forEach items="${pageContext.request.getAttribute('allActivitiesList')}" var="activity"
                       varStatus="status">
                <option value="${activity.id}">${activity.name}, ${activity.category.name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="<fmt:message key="add" bundle="${bundle}"/>">
    </form>
</div>
<form method="post" action="${pageContext.request.contextPath}/app/sortActivities">
    <label><fmt:message key="sort_by" bundle="${bundle}"/></label>
    <select name="sort">
        <option value="name"><fmt:message key="by_name" bundle="${bundle}"/></option>
        <option value="category"><fmt:message key="by_category" bundle="${bundle}"/></option>
    </select>
    <div id="checkboxes">
        <input checked type="checkbox" id="checkAll">
        <label for="checkAll"><fmt:message key="check_all" bundle="${bundle}"/></label>
        <c:forEach items="${pageContext.request.getAttribute('categoriesList')}" var="category" varStatus="status">
            <input checked type="checkbox" , name="${category.name}">
            <label for="${category.name}">${category.name}</label>
        </c:forEach>
    </div>
    <input type="submit" value="<fmt:message key="accept" bundle="${bundle}"/>">
</form>
<h2><fmt:message key="activities" bundle="${bundle}"/></h2>
<table class="table">
    <thead>
    <tr>
        <th><fmt:message key="name" bundle="${bundle}"/></th>
        <th><fmt:message key="category" bundle="${bundle}"/></th>
        <th><fmt:message key="time_spent" bundle="${bundle}"/></th>
        <th></th>
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
            <form method="post" action="${pageContext.request.contextPath}/app/addTime">
                <input type="time" name="time">
                <input type="text" name="activity_id" hidden value="${activity.id}">
                <input type="submit" value="<fmt:message key="send" bundle="${bundle}"/>">
            </form>
        </td>
        <td><a href="${pageContext.request.contextPath}/app/deleteUserActivity?activity_id=${activity.id}"><fmt:message
                key="delete" bundle="${bundle}"/></a>
        </td>

        </tr>
        </tbody>
    </c:forEach>
</table>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/view/js/CheckAllFilter.js"></script>
</body>
</html>