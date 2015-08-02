<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

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
							<tiles:insertAttribute name="body" />
						</li>
					</ol>
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
