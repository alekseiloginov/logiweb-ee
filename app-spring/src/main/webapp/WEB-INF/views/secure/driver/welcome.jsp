<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Driver welcome page</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>

<%--If driver's truck_id is null, don't show a link to his orders because he have no yet.--%>
<%--<c:choose>--%>
    <%--<c:when test="${empty sessionScope.user.getTruck().getId()}">--%>
        <%--<nav>--%>
            <%--<a></a>--%>
        <%--</nav>--%>
        <%--<br>--%>

        <%--<div class="container">--%>
            <%--<c:if test="${pageContext.request.userPrincipal.name != null}">--%>
                <%--<h1>Hello, ${pageContext.request.userPrincipal.name}!</h1>--%>
                <%--&lt;%&ndash;<p>Your email: <c:out value="${sessionScope.user.getEmail()}"/></p>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<p>Your personal driver number: <c:out value="${sessionScope.user.getId()}"/></p>&ndash;%&gt;--%>
            <%--</c:if>--%>

            <%--<form action="<c:url value="/j_spring_security_logout"/>" method="post">--%>
                <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                <%--<input type="submit" value="Logout">--%>
            <%--</form>--%>
        <%--</div>--%>
    <%--</c:when>--%>

    <%--<c:otherwise>--%>
        <nav>
            <a href="orders" title="Order list">Orders</a>
        </nav>
        <br>

        <div class="container">
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <h1>Hello, ${pageContext.request.userPrincipal.name}!</h1>
                <%--<p>Your email: <c:out value="${sessionScope.user.getEmail()}"/></p>--%>
                <%--<p>Your personal driver number: <c:out value="${sessionScope.user.getId()}"/></p>--%>
            </c:if>

            <form action="<c:url value="/j_spring_security_logout"/>" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Logout">
            </form>
        </div>
    <%--</c:otherwise>--%>
<%--</c:choose>--%>

</body>
</html>
