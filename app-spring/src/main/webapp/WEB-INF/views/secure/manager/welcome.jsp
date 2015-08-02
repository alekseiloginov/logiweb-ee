<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true"%>

<%--<nav>--%>
    <%--<a href="trucks" title="Truck list">Trucks</a>--%>
    <%--<a href="drivers" title="Driver list">Drivers</a>--%>
    <%--<a href="orders" title="Order list">Orders</a>--%>
    <%--<a href="freights" title="Freight list">Freights</a>--%>
<%--</nav>--%>

<li class="active">
    <a href="welcome"><i class="fa fa-fw fa-home"></i> Home</a>
</li>

<br />

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h1>Hello, ${pageContext.request.userPrincipal.name}!</h1>
    <hr />
    <p class="lead">New freights waiting for processing: 5</p>
    <p class="lead">Free trucks: 2</p>
    <p class="lead">Free drivers: 1</p>

</c:if>

<form action="<c:url value="/j_spring_security_logout"/>" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" class="btn btn-danger" value="Logout">
</form>
