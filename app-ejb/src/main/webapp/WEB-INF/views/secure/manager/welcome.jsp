<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true"%>
<html>
<head>
    <title>Manager welcome page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<nav>
    <a href="trucks" title="Truck list">Trucks</a>
    <a href="drivers" title="Driver list">Drivers</a>
    <a href="orders?role=manager" title="Order list">Orders</a>
    <a href="freights" title="Freight list">Freights</a>
</nav>
<br>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h1>Hello, ${pageContext.request.userPrincipal.name}!</h1>
        <%--<p>Your email: <c:out value="${sessionScope.user.getEmail()}"/></p>--%>
    </c:if>

    <form action="<c:url value="/j_spring_security_logout"/>" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout">
    </form>

</div>
</body>
</html>
