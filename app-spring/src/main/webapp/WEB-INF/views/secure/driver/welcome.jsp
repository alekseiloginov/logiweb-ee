<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h1><i class="fa fa-user"></i> ${pageContext.request.userPrincipal.name}</h1>
    <hr />
    <p class="lead">Waiting orders: <span class="label label-info">1</span></p>
</c:if>
