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
    <title><fmt:message key="activities" bundle="${bundle}"/></title>
</head>
<body>
<a href="${pageContext.request.contextPath}/app/logout"><fmt:message key="logout" bundle="${bundle}"/></a>
<form method="post" action="${pageContext.request.contextPath}/app/sendAddActivityRequestToAdmin">
    <select required name="activity_id">
        <c:forEach items="${pageContext.request.getAttribute('allActivitiesList')}" var="activity" varStatus="status">
            <option value="${activity.id}">${activity.name}, ${activity.category.name}</option>
        </c:forEach>
    </select>
    <input type="submit" value="<fmt:message key="add" bundle="${bundle}"/>">
</form>
<form method="get" action="${pageContext.request.contextPath}/app/sortActivities">
    <label><fmt:message key="sort_by" bundle="${bundle}"/></label>
    <select required name="sort">
        <option value="name"><fmt:message key="by_name" bundle="${bundle}"/></option>
        <option value="category"><fmt:message key="by_category" bundle="${bundle}"/></option>
    </select>
    <div id="checkboxes">
        <input checked type="checkbox" id="checkAll">
        <label for="checkAll">Check all</label>
        <c:forEach items="${pageContext.request.getAttribute('categoriesList')}" var="category" varStatus="status">
            <input checked type="checkbox" , name="${category.name}">
            <label for="${category.name}">${category.name}</label>
        </c:forEach>
    </div>
    <input type="submit" value="<fmt:message key="accept" bundle="${bundle}"/>">
</form>
<h2><fmt:message key="activities" bundle="${bundle}"/></h2>
<table>
    <tr>
        <th><fmt:message key="name" bundle="${bundle}"/></th>
        <th><fmt:message key="category" bundle="${bundle}"/></th>
        <th><fmt:message key="time_spent" bundle="${bundle}"/></th>
    </tr>
    <c:forEach items="${pageContext.request.getAttribute('activitiesList')}" var="activity" varStatus="status">
        <tr>
            <td>${activity.name}
                <hr/>
            </td>
            <td>${activity.category.name}
                <hr/>
            </td>
            <td>
                ${pageContext.request.getAttribute("timeSpent".concat(activity.name))}
                <form action="${pageContext.request.contextPath}/app/addTime">
                    <input type="time" name="time">
                    <input type="text" name="activity_id" hidden value="${activity.id}">
                    <input type="submit" value="<fmt:message key="send" bundle="${bundle}"/>">
                </form>
                <hr/>
            </td>
            <td><a href="${pageContext.request.contextPath}/app/deleteUserActivity?activity_id=${activity.id}"><fmt:message
                    key="delete" bundle="${bundle}"/></a>
                <hr/>
            </td>
        </tr>
    </c:forEach>
</table>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/view/js/CheckAllFilter.js"></script>
</body>
</html>