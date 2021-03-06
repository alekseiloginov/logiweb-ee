<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--<div id="wrapper">--%>

    <%--<!-- Navigation -->--%>
    <%--<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">--%>
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/"/>">LOGIWEB</a>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                <ul class="dropdown-menu message-dropdown">
                    <sec:authorize access="hasRole('ROLE_MANAGER')">
                    <li class="message-preview">
                        <a href="#">
                            <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                <div class="media-body">
                                    <h5 class="media-heading"><strong>Grisha Chichvarkin</strong>
                                    </h5>
                                    <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:31 PM</p>
                                    <p>Just finished the last order...</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="message-preview">
                        <a href="#">
                            <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                <div class="media-body">
                                    <h5 class="media-heading"><strong>Grisha Chichvarkin</strong>
                                    </h5>
                                    <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                    <p>Ready for a new one!</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="message-preview">
                        <a href="#">
                            <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                <div class="media-body">
                                    <h5 class="media-heading"><strong>Grisha Chichvarkin</strong>
                                    </h5>
                                    <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:33 PM</p>
                                    <p>Please, give me that one with the coffee!!!</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_DRIVER')">
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Aleksei Loginov</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Sorry, no weekends this week!</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </sec:authorize>
                    <li class="message-footer">
                        <a href="#">Read All New Messages</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
                <ul class="dropdown-menu alert-dropdown">
                    <sec:authorize access="hasRole('ROLE_MANAGER')">
                        <li>
                            <a href="freights">New Freights <span class="label label-warning">5</span></a>
                        </li>
                        <li>
                            <a href="trucks">Free Trucks <span class="label label-success">2</span></a>
                        </li>
                        <li>
                            <a href="drivers">Free Drivers <span class="label label-success">1</span></a>
                        </li>
                        <li>
                            <a href="orders">Active Orders <span class="label label-info">3</span></a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="welcome">View All</a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_DRIVER')">
                        <li>
                            <a href="orders">Waiting Orders <span class="label label-info">1</span></a>
                        </li>
                    </sec:authorize>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${pageContext.request.userPrincipal.name} <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="welcome"><i class="fa fa-fw fa-user"></i> Profile</a>
                    </li>
                    <li>
                        <a href="welcome"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                    </li>
                    <li>
                        <a href="welcome"><i class="fa fa-fw fa-gear"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="<c:url value="/j_spring_security_logout"/>"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                    </li>
                </ul>
            </li>
        </ul>
    <%--</nav>--%>

<%--</div>--%>
<%--<!-- /#wrapper -->--%>


<style>
    /*!
 * Start Bootstrap - SB Admin Bootstrap Admin Template (http://startbootstrap.com)
 * Code licensed under the Apache License v2.0.
 * For details, see http://www.apache.org/licenses/LICENSE-2.0.
 */

    /* Global Styles */

    body {
        margin-top: 100px;
        background-color: #222;
    }

    @media(min-width:768px) {
        body {
            margin-top: 50px;
        }
    }

    #wrapper {
        padding-left: 0;
    }

    #page-wrapper {
        width: 100%;
        padding: 0;
        background-color: #fff;
    }

    .huge {
        font-size: 50px;
        line-height: normal;
    }

    @media(min-width:768px) {
        #wrapper {
            padding-left: 225px;
        }

        #page-wrapper {
            padding: 10px;
        }
    }

    /* Top Navigation */

    .top-nav {
        padding: 0 15px;
    }

    .top-nav>li {
        display: inline-block;
        float: left;
    }

    .top-nav>li>a {
        padding-top: 15px;
        padding-bottom: 15px;
        line-height: 20px;
        color: #999;
    }

    .top-nav>li>a:hover,
    .top-nav>li>a:focus,
    .top-nav>.open>a,
    .top-nav>.open>a:hover,
    .top-nav>.open>a:focus {
        color: #fff;
        background-color: #000;
    }

    .top-nav>.open>.dropdown-menu {
        float: left;
        position: absolute;
        margin-top: 0;
        border: 1px solid rgba(0,0,0,.15);
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        background-color: #fff;
        -webkit-box-shadow: 0 6px 12px rgba(0,0,0,.175);
        box-shadow: 0 6px 12px rgba(0,0,0,.175);
    }

    .top-nav>.open>.dropdown-menu>li>a {
        white-space: normal;
    }

    ul.message-dropdown {
        padding: 0;
        max-height: 250px;
        overflow-x: hidden;
        overflow-y: auto;
    }

    li.message-preview {
        width: 275px;
        border-bottom: 1px solid rgba(0,0,0,.15);
    }

    li.message-preview>a {
        padding-top: 15px;
        padding-bottom: 15px;
    }

    li.message-footer {
        margin: 5px 0;
    }

    ul.alert-dropdown {
        width: 200px;
    }

    /* Side Navigation */

    @media(min-width:768px) {
        .side-nav {
            position: fixed;
            top: 51px;
            left: 225px;
            width: 225px;
            margin-left: -225px;
            border: none;
            border-radius: 0;
            overflow-y: auto;
            background-color: #222;
            bottom: 0;
            overflow-x: hidden;
            padding-bottom: 40px;
        }

        .side-nav>li>a {
            width: 225px;
        }

        .side-nav li a:hover,
        .side-nav li a:focus {
            outline: none;
            background-color: #000 !important;
        }
    }

    .side-nav>li>ul {
        padding: 0;
    }

    .side-nav>li>ul>li>a {
        display: block;
        padding: 10px 15px 10px 38px;
        text-decoration: none;
        color: #999;
    }

    .side-nav>li>ul>li>a:hover {
        color: #fff;
    }

    /* Flot Chart Containers */

    .flot-chart {
        display: block;
        height: 400px;
    }

    .flot-chart-content {
        width: 100%;
        height: 100%;
    }

    /* Custom Colored Panels */

    .huge {
        font-size: 40px;
    }

    .panel-green {
        border-color: #5cb85c;
    }

    .panel-green > .panel-heading {
        border-color: #5cb85c;
        color: #fff;
        background-color: #5cb85c;
    }

    .panel-green > a {
        color: #5cb85c;
    }

    .panel-green > a:hover {
        color: #3d8b3d;
    }

    .panel-red {
        border-color: #d9534f;
    }

    .panel-red > .panel-heading {
        border-color: #d9534f;
        color: #fff;
        background-color: #d9534f;
    }

    .panel-red > a {
        color: #d9534f;
    }

    .panel-red > a:hover {
        color: #b52b27;
    }

    .panel-yellow {
        border-color: #f0ad4e;
    }

    .panel-yellow > .panel-heading {
        border-color: #f0ad4e;
        color: #fff;
        background-color: #f0ad4e;
    }

    .panel-yellow > a {
        color: #f0ad4e;
    }

    .panel-yellow > a:hover {
        color: #df8a13;
    }
</style>