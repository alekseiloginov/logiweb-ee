<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true"%>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h1><i class="fa fa-user"></i> ${pageContext.request.userPrincipal.name}</h1>
    <hr />
    <p class="lead">New freights waiting for processing: <span class="label label-warning">5</span></p>
    <p class="lead">Free trucks: <span class="label label-success">2</span></p>
    <p class="lead">Free drivers: <span class="label label-success">1</span></p>
    <p class="lead">Active orders: <span class="label label-info">3</span></p>

</c:if>
