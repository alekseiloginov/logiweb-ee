<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p class="lead">Enter your email</p>

<form name="remindForm" class="form-remind" action="sendpass" method="post">
  <input type="email" id="email" name="email" class="form-control" placeholder="Email address" required>
  <button class="btn btn-lg btn-success btn-block" type="submit">Remind password</button>
</form>

<div>
  <c:if test="${not empty error}">
    <p class="db_error">${error}</p>
  </c:if>
  <p class="success"></p>
</div>

<style>
  .form-remind {
    max-width: 330px;
    padding: 15px;
    margin: 0 auto;
  }
  .form-remind .form-remind-heading,
  .form-remind .checkbox {
    margin-bottom: 10px;
  }
  .form-remind .checkbox {
    font-weight: normal;
  }
  .form-remind .form-control {
    position: relative;
    height: auto;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    padding: 10px;
    font-size: 16px;
  }
  .form-remind .form-control:focus {
    z-index: 2;
  }
  .form-remind input[id="email"] {
    margin-bottom: 20px;
  }
</style>