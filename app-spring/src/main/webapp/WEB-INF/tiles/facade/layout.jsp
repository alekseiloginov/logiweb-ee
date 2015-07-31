<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Bootstrap Core CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

	<!-- Font Awesome CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" >

    <!-- My CSS file -->
    <%--<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">--%>

	<title><tiles:getAsString name="title"/></title>
</head>
<body>

<div class="container">

    <!-- Header -->
    <tiles:insertAttribute name="header" />

    <%-- Database Login Error / Logout Success Bootstrap Well --%>
    <c:if test="${not empty error}">
        <div class="well well-sm">
            <p class="lead db_error"><br />${error}</p>
        </div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="well well-sm">
            <p class="lead db_success"><br />${success}</p>
        </div>
    </c:if>

    <!-- Body -->
    <div class="jumbotron">
        <tiles:insertAttribute name="body" />
    </div>

    <!-- Footer -->
    <tiles:insertAttribute name="footer" />

</div>

<style>
    /* Space out content a bit */
    body {
        padding-top: 20px;
        padding-bottom: 20px;
    }

    /* Everything but the jumbotron gets side spacing for mobile first views */
    .header,
    .marketing,
    .footer {
        padding-right: 15px;
        padding-left: 15px;
    }

    /* Custom page header */
    .header {
        padding-bottom: 20px;
        border-bottom: 1px solid #e5e5e5;
    }
    /* Make the masthead heading the same height as the navigation */
    .header h3 {
        margin-top: 0;
        margin-bottom: 0;
        line-height: 40px;
    }

    /* Custom page footer */
    .footer {
        padding-top: 19px;
        color: #777;
        border-top: 1px solid #e5e5e5;
    }

    /* Customize container */
    @media (min-width: 768px) {
        .container {
            max-width: 730px;
        }
    }
    .container-narrow > hr {
        margin: 30px 0;
    }

    /* Main marketing message and sign up button */
    .jumbotron {
        text-align: center;
        border-bottom: 1px solid #e5e5e5;
    }
    .jumbotron .btn {
        padding: 14px 24px;
        font-size: 21px;
    }

    /* Supporting marketing content */
    .marketing {
        margin: 40px 0;
    }
    .marketing p + h4 {
        margin-top: 28px;
    }

    /* Responsive: Portrait tablets and up */
    @media screen and (min-width: 768px) {
        /* Remove the padding we set earlier */
        .header,
        .marketing,
        .footer {
            padding-right: 0;
            padding-left: 0;
        }
        /* Space out the masthead */
        .header {
            margin-bottom: 30px;
        }
        /* Remove the bottom border on the jumbotron for visual effect */
        .jumbotron {
            border-bottom: 0;
        }
    }

    .error {
        text-align: center;
        color: red;
    }
    .db_error {
        text-align: center;
        color: red;
    }
    .success {
        text-align: center;
        color: green;
    }
    .db_success {
        text-align: center;
        color: green;
    }
</style>

<!-- jQuery library -->
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- My JavaScript file -->
<script src="<c:url value="/resources/js/script.js"/>"></script>
</body>
</html>
