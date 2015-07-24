<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>404</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>
<body>
<br><br>

<div class="container page-not-found">

    <h2>Page not found!</h2>

    <img class="page-not-found" src="<c:url value="/resources/images/polar-bear.jpg"/>" alt="404">

    <form action="landing" method="GET">
        <input type="submit" value="Go to the main page">
    </form>
</div>
</body>
</html>