<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
    .menu {
        font-size: 16px;
    }
</style>
<%--<div id="wrapper">--%>
    <%--<!-- Navigation -->--%>
    <%--<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">--%>

        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li
                    <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/welcome'}">
                        class="active"
                    </c:if>
                    >
                    <a class="menu" href="welcome"><i class="fa fa-fw fa-home"></i> Home</a>
                </li>
                <sec:authorize access="hasRole('ROLE_MANAGER')">
                <li
                    <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/trucks'}">
                        class="active"
                    </c:if>
                    >
                    <a class="menu" href="trucks"><i class="fa fa-fw fa-bus"></i> Trucks</a>
                </li>
                <li
                    <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/drivers'}">
                        class="active"
                    </c:if>
                    >
                    <a class="menu" href="drivers"><i class="fa fa-fw fa-user"></i> Drivers</a>
                </li>
                <li
                    <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/freights'}">
                            class="active"
                    </c:if>
                    >
                    <a class="menu" href="freights"><i class="fa fa-fw fa-cube"></i> Freights</a>
                </li>
                </sec:authorize>
                <li
                    <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/orders'}">
                        class="active"
                    </c:if>
                    >
                    <a class="menu" href="orders"><i class="fa fa-fw fa-file-text-o"></i> Orders</a>
                </li>
                <sec:authorize access="hasRole('ROLE_MANAGER')">
                <li
                    <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/logiweb-ee/locations'}">
                        class="active"
                    </c:if>
                    >
                    <a class="menu" href="locations"><i class="fa fa-fw fa-map-marker"></i> Locations</a>
                </li>
                </sec:authorize>
                <li>
                    <a class="menu" href="javascript:" data-toggle="collapse" data-target="#extra">
                        <i class="fa fa-fw fa-table"></i> Other Tables <i class="fa fa-fw fa-caret-down"></i>
                    </a>
                    <ul id="extra" class="collapse">
                        <li>
                            <a class="menu" href="#"><i class="fa fa-fw fa-user"></i> Managers</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    <%--</nav>--%>

<%--</div>--%>
<%--<!-- /#wrapper -->--%>
