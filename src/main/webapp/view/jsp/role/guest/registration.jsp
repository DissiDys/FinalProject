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
    <title><fmt:message key="registration" bundle="${bundle}"/></title>
</head>

<body>
    <div>
        <a href="${pageContext.request.contextPath}/index.jsp" class="onMain"><fmt:message key="on_main" bundle="${bundle}"/></a>
        <a href="${EnLang}" class="localizationEN">EN</a>
        <a href="${UkrLang}" class="localizationUA">UKR</a>
    </div>

    <form method="post" action="${pageContext.request.contextPath}/app/registration">
        <div>
            <input required type="text" id="login" name="login" placeholder=" "
            pattern="(?=^)[a-zA-Z1-9_!#$%*]*(?=$)" autocomplete="off" onchange="clearErrorMsg();"
            class="registration">

            <label for="login"><fmt:message key = "login" bundle = "${bundle}"/></label>

            <div class="errorMsg" id="errorMsg">
                <c:if test="${pageContext.request.getAttribute('not_unique_login')}">
                    <fmt:message key="login_exist" bundle="${bundle}"/>
                </c:if>
            </div>

            <div class="requirements">
                <fmt:message key = "invalid_login" bundle = "${bundle}"/>
            </div>
        </div>

        <div>
            <input required type="password"  id="password" name="password" placeholder=" "
            pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" onchange="check()" autocomplete="off"
            class="registration">
            <label for="password"><fmt:message key = "password" bundle = "${bundle}"/></label>

            <div class="requirements">
                <fmt:message key = "invalid_password" bundle = "${bundle}"/>
            </div>
        </div>

        <div>
            <input required type="password" id="confirm_password" name="confirm_password" placeholder=" "
            autocomplete="off" class="registration">
            <label for="confirm_password"><fmt:message key = "confirm_password" bundle = "${bundle}"/></label>
            <div class="requirements">
                <fmt:message key = "password_not_equal" bundle = "${bundle}"/>
            </div>
        </div>


        <input class="button" type="submit" value="<fmt:message key = "sign_up" bundle = "${bundle}"/>">
    </form>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/LoginRegistrationValidateFunctions.js"></script>
</body>
</html>