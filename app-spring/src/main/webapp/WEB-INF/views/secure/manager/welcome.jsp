<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true"%>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h1>Hello, ${pageContext.request.userPrincipal.name}!</h1>
    <hr />
    <p class="lead">New freights waiting for processing: 5</p>
    <p class="lead">Free trucks: 2</p>
    <p class="lead">Free drivers: 1</p>

</c:if>
