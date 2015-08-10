<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="icon" type="image/x-icon" href="<c:url value="/resources/images/favicon.ico"/>">

	<!-- TEMPLATE URL: ironsummitmedia.github.io/startbootstrap-sb-admin -->

	<!-- Bootstrap Core CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

	<!-- Font Awesome CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" >

    <!-- My CSS file -->
    <%--<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">--%>

	<title><tiles:getAsString name="title"/></title>
</head>
<body>

<div id="wrapper">
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu" />
	</nav>

	<!-- Body -->
	<div id="page-wrapper">
		<div class="container-fluid">
			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						Dashboard
						<%--<small>Statistics Overview</small>--%>
					</h1>
					<ol class="breadcrumb">
						<li class="active">
							<i class="fa fa-dashboard"></i> Dashboard
						</li>
                        <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/welcome'}">
                            <li>
                                <a href="welcome"><i class="fa fa-fw fa-home"></i> Home</a>
                            </li>
                        </c:if>
                        <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/trucks'}">
                            <li>
                                <a href="trucks"><i class="fa fa-fw fa-bus"></i> Trucks</a>
                            </li>
                        </c:if>
                        <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/drivers'}">
                            <li>
                                <a href="drivers"><i class="fa fa-fw fa-user"></i> Drivers</a>
                            </li>
                        </c:if>
                        <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/freights'}">
                            <li>
                                <a href="freights"><i class="fa fa-fw fa-cube"></i> Freights</a>
                            </li>
                        </c:if>
                        <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/orders'}">
                            <li>
                                <a href="orders"><i class="fa fa-fw fa-file-text-o"></i> Orders</a>
                            </li>
                        </c:if>
					</ol>
				</div>
			</div>
			<!-- /.row -->

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/welcome'}">
                                    <i class="fa fa-fw fa-home"></i> Home
                                </c:if>
                                <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/trucks'}">
                                    <i class="fa fa-fw fa-bus"></i> Trucks
                                </c:if>
                                <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/drivers'}">
                                    <i class="fa fa-fw fa-user"></i> Drivers
                                </c:if>
                                <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/freights'}">
                                    <i class="fa fa-fw fa-cube"></i> Freights
                                </c:if>
                                <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/orders'}">
                                    <i class="fa fa-fw fa-file-text-o"></i> Orders
                                </c:if>
                            </h3>
                        </div>
                        <div class="panel-body">
                            <div id="body">
                                <tiles:insertAttribute name="body" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->

		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /#page-wrapper -->
</div>

<tiles:insertAttribute name="footer" />


<!-- jQuery library -->
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- My JavaScript file -->
<script src="<c:url value="/resources/js/script.js"/>"></script>


<!-- JQuery UI - Overcast theme styles -->
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.theme.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.structure.css"/>" />
<!-- jTable styles -->
<link rel="stylesheet" href="<c:url value="/resources/jtable/themes/metro/lightgray/jtable.min.css"/>" />
<!-- jTable: CSS file for validation engine -->
<link rel="stylesheet" href="<c:url value="/resources/css/validationEngine.jquery.css"/>" />

<!-- jQuery UI lib -->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js" type="text/javascript"></script>
<!-- jTable script file -->
<script src="<c:url value="/resources/jtable/jquery.jtable.min.js"/>" type="text/javascript"></script>
<!-- jTable: Javascript files for validation engine -->
<script src="<c:url value="/resources/js/jquery.validationEngine.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery.validationEngine-en.js"/>" type="text/javascript"></script>
<!-- My jTable script files -->
<script src="<c:url value="/resources/js/truck_script.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/resources/js/driver_script.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/resources/js/order_manager_script.js"/>" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/resources/js/freight_script.js"/>" type="text/javascript" charset="utf-8"></script>

</body>
</html>
