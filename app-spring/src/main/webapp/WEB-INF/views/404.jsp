<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" >

    <!-- My CSS file -->
    <%--<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">--%>

    <title>404</title>
</head>
<body>

<div class="container">
    <!-- Header -->
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <%--<li role="presentation" class="active"><a href="">Home</a></li>--%>
                <li role="presentation" class="active"><a href="#" id="myBtn" >Login</a></li>
            </ul>
        </nav>
        <a href="<c:url value="/"/>">
            <img src="<c:url value="/resources/images/logiweb.png"/>" class="img-responsive" alt="Logiweb logo">
        </a>

        <!-- Bootstrap Modal Login Form -->
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header" style="padding: 35px 50px;">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
                    </div>

                    <div class="modal-body" style="padding: 40px 50px;">

                        <form role="form" name="loginForm" action="<c:url value="/j_spring_security_check"/>" method="post"
                              onsubmit="return validateLoginForm()">

                            <div class="form-group">
                                <label for="username"><span class="glyphicon glyphicon-user"></span> Username</label>
                                <input type="text" class="form-control" id="username" name="email" placeholder="Enter username" required autofocus>
                            </div>

                            <div class="form-group">
                                <label for="password"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
                            </div>

                            <div class="checkbox">
                                <label><input type="checkbox" value="" checked>Remember me</label>
                            </div>

                            <%-- Error/Success Message --%>
                            <p class="error"></p>
                            <p class="success"></p>

                            <%-- Login Button --%>
                            <button type="submit" class="btn btn-success btn-block">
                                <span class="glyphicon glyphicon-off"></span> Login
                            </button>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <%-- Cancel Button --%>
                        <%--<button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal">--%>
                        <%--<span class="glyphicon glyphicon-remove"></span> Cancel--%>
                        <%--</button>--%>

                        <%-- Fill Manager Button --%>
                        <button id="fill-manager" type="submit" class="btn btn-xs btn-default pull-left">Fill manager</button>

                        <%-- Fill Driver Button --%>
                        <button id="fill-driver" type="submit" class="btn btn-xs btn-default pull-left">Fill driver</button>

                        <%-- Sign Up Link --%>
                        <p>Not a member? <a href="registration">Sign Up</a></p>

                        <%-- Forgot Password Link --%>
                        <p>Forgot <a href="#">Password?</a></p>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <!-- Body -->
    <img id="bear" src="<c:url value="/resources/images/polar-bear.jpg"/>" alt="404">
    <div class="well">
        <h1>Page Not Found</h1>
    </div>

    <!-- Footer -->
    <footer class="footer">
        <p>&copy; T-Systems 2015</p>
    </footer>

</div>

<style>
    /* Space out content a bit */
    body {
        padding-top: 20px;
        padding-bottom: 20px;
    }

    /* Everything but the jumbotron gets side spacing for mobile first views */
    .header,
    .marketing,
    .footer {
        padding-right: 15px;
        padding-left: 15px;
    }

    /* Custom page header */
    .header {
        padding-bottom: 20px;
        border-bottom: 1px solid #e5e5e5;
    }
    /* Make the masthead heading the same height as the navigation */
    .header h3 {
        margin-top: 0;
        margin-bottom: 0;
        line-height: 40px;
    }

    /* Custom page footer */
    .footer {
        padding-top: 19px;
        color: #777;
        border-top: 1px solid #e5e5e5;
    }

    /* Customize container */
    @media (min-width: 768px) {
        .container {
            max-width: 730px;
        }
    }
    .container-narrow > hr {
        margin: 30px 0;
    }

    /* Main marketing message and sign up button */
    .jumbotron {
        text-align: center;
        border-bottom: 1px solid #e5e5e5;
    }
    .jumbotron .btn {
        padding: 14px 24px;
        font-size: 21px;
    }

    /* Supporting marketing content */
    .marketing {
        margin: 40px 0;
    }
    .marketing p + h4 {
        margin-top: 28px;
    }

    /* Responsive: Portrait tablets and up */
    @media screen and (min-width: 768px) {
        /* Remove the padding we set earlier */
        .header,
        .marketing,
        .footer {
            padding-right: 0;
            padding-left: 0;
        }
        /* Space out the masthead */
        .header {
            margin-bottom: 30px;
        }
        /* Remove the bottom border on the jumbotron for visual effect */
        .jumbotron {
            border-bottom: 0;
        }
    }

    .modal-header, h4, .close {
        background-color: #5cb85c;
        color:white !important;
        text-align: center;
        font-size: 30px;
    }
    .modal-footer {
        background-color: #f9f9f9;
    }

    .error {
        text-align: center;
        color: red;
    }
    .db_error {
        text-align: center;
        color: red;
    }
    .success {
        text-align: center;
        color: green;
    }
    .db_success {
        text-align: center;
        color: green;
    }

    #bear {
        display: block;
        margin-left: auto;
        margin-right: auto
    }
    h1 {
        text-align: center;
        color: white;
    }
    .well {
        background-color: #5CB85C;
    }
</style>

<!-- jQuery library -->
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- My JavaScript file -->
<script src="<c:url value="/resources/js/script.js"/>"></script>
</body>
</html>