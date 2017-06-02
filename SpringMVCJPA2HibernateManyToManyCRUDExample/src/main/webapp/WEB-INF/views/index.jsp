<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Spring MVC Internationalization i18n Example</title>
</head>
<body>

    <h1>Spring MVC Internationalization i18n Example</h1>

    Language : <a href="?lang=en">English</a> | <a href="?lang=fr">French</a>

    <p>${message}</p>

    <p><spring:message code="welcome.greeting" arguments="${startMeeting}"/></p>

    Current Locale : ${pageContext.response.locale} / ${locale}

</body>
</html>