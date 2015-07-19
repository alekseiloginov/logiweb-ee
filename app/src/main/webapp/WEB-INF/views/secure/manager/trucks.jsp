<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Trucks</title>
    <!-- My styles -->
    <link rel="stylesheet" href="../../../../resources/css/style.css" />
    <!-- JQuery UI - Overcast theme styles -->
    <link rel="stylesheet" href="../../../../resources/css/jquery-ui.css" />
    <link rel="stylesheet" href="../../../../resources/css/jquery-ui.theme.css" />
    <link rel="stylesheet" href="../../../../resources/css/jquery-ui.structure.css" />
    <!-- jTable styles -->
    <link rel="stylesheet" href="../../../../resources/jtable/themes/metro/lightgray/jtable.min.css" />
    <!-- jTable: CSS file for validation engine -->
    <link rel="stylesheet" href="../../../../resources/css/validationEngine.jquery.css" />
</head>
<body>

<nav>
    <a href="drivers" title="Driver list">Drivers</a>
    <a href="orders?role=manager" title="Order list">Orders</a>
    <a href="freights" title="Freight list">Freights</a>
    <a href="Welcome.do?role=manager" title="Welcome page">Home</a>
</nav>
<br><br>

<div class="container">
    <div id="TruckTableContainer"></div>
</div>

<!-- jQuery lib -->
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
<!-- jQuery UI lib -->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js" type="text/javascript"></script>
<!-- jTable script file -->
<script src="../../../../resources/jtable/jquery.jtable.min.js" type="text/javascript"></script>
<!-- My jTable script file -->
<script src="../../../../resources/js/truck_script.js" type="text/javascript" charset="utf-8"></script>
<!-- jTable: Javascript files for validation engine -->
<script src="../../../../resources/js/jquery.validationEngine.js" type="text/javascript"></script>
<script src="../../../../resources/js/jquery.validationEngine-en.js" type="text/javascript"></script>
<%--<script src="../../../../resources/js/jquery.validationEngine-de.js" type="text/javascript"></script>--%>
<!-- jTable DE localization script file -->
<%--<script src="../../../../resources/jtable/localization/jquery.jtable.de.js" type="text/javascript" charset="utf-8"></script>--%>
</body>
</html>
