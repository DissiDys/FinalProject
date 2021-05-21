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
    <title><fmt:message key="sign_in" bundle="${bundle}"/></title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/app/onMain" class="onMain"><fmt:message key="on_main" bundle="${bundle}"/></a>
    <a href="${EnLang}" class="localizationEN">EN</a>
    <a href="${UkrLang}" class="localizationUA">UKR</a>
</div>

<form method="post" action="${pageContext.request.contextPath}/app/login">
    <div class="successReg" id="successReg">
        <c:if test="${pageContext.request.getAttribute('success_reg')}">
            <fmt:message key="success_reg" bundle="${bundle}"/>
        </c:if>
    </div>

    <div>
        <input type="text" id="login" name="name" placeholder=" " autocomplete="off">
        <label for="login"><fmt:message key="login" bundle="${bundle}"/></label>
    </div>

    <div>
        <input type="password" id="password" name="password" placeholder=" " autocomplete="off"
               onfocus="clearErrorMsg()">
        <label for="password"><fmt:message key="password" bundle="${bundle}"/></label>
    </div>

    <div class="errorMsg" id="errorMsg">
        <c:if test="${pageContext.request.getAttribute('user_already_logged')}">
            <fmt:message key="user_already_logged" bundle="${bundle}"/>
        </c:if>
        <c:if test="${pageContext.request.getAttribute('wrong_data')}">
            <fmt:message key="wrong_data" bundle="${bundle}"/>
        </c:if>
    </div>
    <input class="button" type="submit" value="<fmt:message key = "sign_in" bundle = "${bundle}"/>">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/view/js/LoginRegistrationValidateFunctions.js"></script>
</form>
</body>
</html>