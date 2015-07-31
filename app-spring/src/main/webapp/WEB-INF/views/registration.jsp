<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p class="lead">Enter your name, surname, email and password</p>

<form name="regForm" class="form-signup" action="Register.go" onsubmit="return validateRegForm()" method="post">
    <input type="text" id="name" name="name" class="form-control" placeholder="Name"required autofocus>
    <input type="text" id="surname" name="surname" class="form-control" placeholder="Surname" required>
    <input type="email" id="email" name="email" class="form-control" placeholder="Email address" required>
    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
    <button class="btn btn-lg btn-success btn-block" type="submit">Sign up</button>
</form>

<%-- Fill Manager Button --%>
<%--<button id="fill-manager" type="submit" class="btn btn-xs btn-default pull-left">Fill form</button>--%>

<div>
    <c:if test="${not empty error}">
        <p class="db_error">${error}</p>
    </c:if>
    <p class="error"></p>
</div>

<style>
    .form-signup {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
    }
    .form-signup .form-signup-heading,
    .form-signup .checkbox {
        margin-bottom: 10px;
    }
    .form-signup .checkbox {
        font-weight: normal;
    }
    .form-signup .form-control {
        position: relative;
        height: auto;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        padding: 10px;
        font-size: 16px;
    }
    .form-signup .form-control:focus {
        z-index: 2;
    }
    .form-signup input[id="name"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }
    .form-signup input[id="surname"] {
        margin-bottom: -1px;
        border-radius: 0;
    }
    .form-signup input[id="email"] {
        margin-bottom: -1px;
        border-radius: 0;
    }
    .form-signup input[id="password"] {
        margin-bottom: 20px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>