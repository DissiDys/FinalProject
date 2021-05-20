<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/style.css">
    <title>Time-Tracking</title>
</head>
<body>
    <fmt:setLocale value = '${pageContext.request.getSession(false).getAttribute("lang")}'/>
    <fmt:setBundle basename = "translate" var ="bundle"/>

    <c:url value="" var="EnLang">
        <c:param name="lang" value="en"/>
    </c:url>

    <c:url value="" var="UkrLang">
        <c:param name="lang" value="ukr"/>
    </c:url>

    <div>
        <a href="${EnLang}" class="localizationEN">EN</a>
        <a href="${UkrLang}" class="localizationUA">UKR</a>
    </div>
    <form action="${pageContext.request.contextPath}/view/login.jsp">
        <button class="btn"><fmt:message key = "sign_in" bundle = "${bundle}"/></button>
    </form>
    <form action="${pageContext.request.contextPath}/view/registration.jsp">
        <button class="btn"><fmt:message key = "sign_up" bundle = "${bundle}"/></button>
    </form>
</body>
</html>
