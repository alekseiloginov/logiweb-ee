<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Logiweb</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>
<br><br><br><br><br>

<div class="container">
<h2>Welcome to the Logiweb logistics web service!</h2>
<br>
<h4>I am a</h4>
<form action="login_manager" method="GET">
    <input type="submit" value="Manager">
</form>
<form action="login_driver" method="GET">
    <input type="submit" value="Driver">
</form>
</div>
</body>
</html>