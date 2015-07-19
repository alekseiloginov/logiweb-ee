<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager welcome page</title>
    <link rel="stylesheet" href="../../../../resources/css/style.css">
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
    <h1>Hello, <c:out value="${sessionScope.user.getName()}"/>!</h1>
    <p>Your email: <c:out value="${sessionScope.user.getEmail()}"/></p>

    <form action="Logout" method="post">
        <input type="submit" value="Logout">
    </form>

</div>
</body>
</html>
