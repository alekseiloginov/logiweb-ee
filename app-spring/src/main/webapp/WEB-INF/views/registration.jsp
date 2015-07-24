<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head lang="en">
  <meta charset="UTF-8">
  <title>Manager sign-up Page</title>
  <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>

<body onload='document.regForm.name.focus();'>
<nav>
  <a href="landing" title="Go to the welcome page">Home</a>
  <a href="login_manager" title="Go to the manager login page">Log in</a>

  <button id="manager">Fill form</button>
</nav>
<br>

<div class="container">
  <h2>Enter your name, surname, email and password</h2>

  <form name="regForm" action="Register.go" onsubmit="return validateRegForm()" method="post">
    <input id="name" type="text" name="name" placeholder="name"><br>
    <input id="surname" type="text" name="surname" placeholder="surname"><br>
    <input id="email" type="text" name="email" placeholder="email"><br>
    <input id="password" type="password" name="password" placeholder="password"><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Sign-up">
  </form>

  <div>
    <c:if test="${not empty error}">
      <p class="db_error">${error}</p>
    </c:if>
    <p class="error">Error message</p>
  </div>
</div>

<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/resources/js/script.js"/>" type="text/javascript" charset="utf-8"></script>
</body>
</html>