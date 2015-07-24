<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head lang="en">
  <meta charset="UTF-8">
  <title>Manager login page</title>
  <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
</head>

<body onload='document.loginForm.email.focus();'>
<nav>
  <a href="landing" title="Go to the welcome page">Home</a>
  <a href="registration" title="Go to the registration">Sign up</a>

  <button id="manager">Fill form</button>
</nav>
<br>

<div class="container">
  <h2>Enter your email and password</h2>

  <form name="loginForm" action="<c:url value="/j_spring_security_check"/>" onsubmit="return validateLoginForm()" method="post">
    <%--<input type="hidden" name="role" value="manager">--%>
    <input id="email" type="text" name="email" placeholder="email"><br>
    <input id="password" type="password" name="password" placeholder="password"><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Login">
  </form>

  <div>
    <c:if test="${not empty error}">
      <p class="db_error">${error}</p>
    </c:if>
    <c:if test="${not empty success}">
      <p class="db_success">${success}</p>
    </c:if>
    <p class="error"></p>
    <p class="success"></p>
  </div>
</div>

<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<c:url value="/resources/js/script.js"/>" type="text/javascript" charset="utf-8"></script>
</body>
</html>
