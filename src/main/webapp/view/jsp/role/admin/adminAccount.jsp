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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/style.css">
    <link rel="shortcut icon" href="https://img.icons8.com/ios/452/clock--v1.png" type="image/png">
    <title><fmt:message key="admin_account" bundle="${bundle}"/></title>
</head>
    <body>
        <div>
            <a href="${pageContext.request.contextPath}/app/logout" class="logout"><fmt:message key="logout" bundle="${bundle}"/></a>
            <a href="${EnLang}" class="localizationEN">EN</a>
            <a href="${UkrLang}" class="localizationUA">UKR</a>
        </div>

        <div>
            <br/>
            <br/>
            <br/>
            <a href="${pageContext.request.contextPath}/app/usersList"><fmt:message key="manage_users" bundle="${bundle}"/></a>
            <br/>
            <a href="${pageContext.request.contextPath}/app/activitiesCategories"><fmt:message key="activities_categories" bundle="${bundle}"/></a>
            <br/>
            <a href="${pageContext.request.contextPath}/app/activities"><fmt:message key="activities" bundle="${bundle}"/></a>
            <br/>
            <a href="${pageContext.request.contextPath}/app/accept"><fmt:message key="accept" bundle="${bundle}"/></a>
        </div>
    </body>
</html>
