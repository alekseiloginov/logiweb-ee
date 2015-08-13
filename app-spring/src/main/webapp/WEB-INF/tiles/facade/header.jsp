<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" required autofocus>
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
                    <p>Forgot <a href="remind">Password?</a></p>
                </div>
            </div>

        </div>
    </div>

    <style>
        .modal-header, h4, .close {
            background-color: #5cb85c;
            color:white !important;
            text-align: center;
            font-size: 30px;
        }
        .modal-footer {
            background-color: #f9f9f9;
        }
    </style>

</div>