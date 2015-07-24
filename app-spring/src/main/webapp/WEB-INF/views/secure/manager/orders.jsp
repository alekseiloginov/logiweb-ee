<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Orders</title>
    <!-- My styles -->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
    <!-- JQuery UI - Overcast theme styles -->
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.theme.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.structure.css"/>" />
    <!-- jTable styles -->
    <link rel="stylesheet" href="<c:url value="/resources/jtable/themes/metro/lightgray/jtable.min.css"/>" />
</head>

<body>
<nav>
    <a href="trucks" title="Truck list">Trucks</a>
    <a href="drivers" title="Driver list">Drivers</a>
    <a href="freights" title="Freight list">Freights</a>
    <a href="welcome" title="Welcome page">Home</a>
</nav>
<br><br>

<div class="container">
    <div id="OrderTableContainer"></div>
</div>

<!-- jQuery lib -->
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
<!-- jQuery UI lib -->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js" type="text/javascript"></script>
<!-- jTable script file -->
<script src="<c:url value="/resources/jtable/jquery.jtable.min.js"/>" type="text/javascript"></script>
<!-- My jTable script file -->
<script src="<c:url value="/resources/js/order_manager_script.js"/>" type="text/javascript" charset="utf-8"></script>
</body>
</html>
