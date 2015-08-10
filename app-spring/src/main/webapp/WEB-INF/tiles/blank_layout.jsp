<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="icon" type="image/x-icon" href="<c:url value="/resources/images/favicon.ico"/>">

  <!-- Bootstrap Core CSS -->
  <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>

  <%--<!-- Font Awesome CSS -->--%>
  <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" >--%>

  <!-- My CSS file -->
  <%--<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">--%>

  <title><tiles:getAsString name="title"/></title>
</head>
<body>

<%--<div class="container">--%>

  <!-- Body -->
  <tiles:insertAttribute name="body" />

<%--</div>--%>

<%--<!-- jQuery library -->--%>
<%--<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>--%>
<%--<!-- Bootstrap Core JavaScript -->--%>
<%--<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>--%>
<%--<!-- My JavaScript file -->--%>
<%--<script src="<c:url value="/resources/js/script.js"/>"></script>--%>
</body>
</html>
