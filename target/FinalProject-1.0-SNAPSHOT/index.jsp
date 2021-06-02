<%@ page contentType="text/html; utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <fmt:setLocale value = '${pageContext.request.getSession(false).getAttribute("lang")}'/>
    <fmt:setBundle basename = "translate" var ="bundle"/>

    <c:url value="" var="EnLang">
        <c:param name="lang" value="en"/>
    </c:url>

    <c:url value="" var="UkrLang">
        <c:param name="lang" value="ukr"/>
    </c:url>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/loginRegistrartion.css">
    <link rel="shortcut icon" href="https://img.icons8.com/ios/452/clock--v1.png" type="image/png">
    <title><fmt:message key = "time_tracking" bundle = "${bundle}"/></title>
</head>
<body>
    <div>
        <a href="${EnLang}" class="localizationEN">EN</a>
        <a href="${UkrLang}" class="localizationUA">UKR</a>
    </div>
    <form action="${pageContext.request.contextPath}/view/jsp/role/guest/login.jsp">
        <input type="submit" class="btn" value="<fmt:message key = "sign_in" bundle = "${bundle}"/>"/>
    </form>
    <form action="${pageContext.request.contextPath}/view/jsp/role/guest/registration.jsp">
        <input type="submit" class="btn" value="<fmt:message key = "sign_up" bundle = "${bundle}"/>"/>
    </form>
</body>
</html>
