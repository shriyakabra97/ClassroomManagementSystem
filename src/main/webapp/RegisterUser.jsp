<%@ page import="com.spe.ClassroomManagementSystem.Models.TA" %>
<%@ page import="org.springframework.http.ResponseEntity" %>
<%@ page import="org.springframework.web.bind.annotation.RequestMapping" %>
<%--<%@ page import="org.springframework.web.bind.annotation.RequestBody" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css-1">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="/css/index.css">
    <style>
        body{
            font-family: Ubuntu;
            background: none;
        }
        nav a{ color: white;
        }
        form label{
            float: left;
        }

    </style>



</head>
<body>
<nav class="navbar fixed-top navbar-light" style="background-color: #50c7e5; ">
    <!-- Navbar content -->
    <a class="navbar-brand" href="#">IIIT-B Clasroom Manager</a>
    <ul class="nav navbar-nav navbar-left">
        <li><a href="RegisterUser.jsp" class="active"> Add User </a></li>
        <li><a href="">View Requests</a></li>
        <li><a href="">Add timetable</a> </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/destroy" style="margin-right: 10px"><span class="glyphicon glyphicon-log-in" ></span> Logout</a></li>
    </ul>
</nav>

<br>
<div class="container">
<div class="login-container" style="width: 500px; margin: 30px auto">
<div class="form-box">
    <form action="/register" method="post">
        <label>User Type </label>
        <select class="form-control" id="usertype" name="usertype" required style="border-radius: 5px 5px 5px 5px">
            <option name="select_usertype" value="">Select User Type</option>
            <option name="professor" value="professor">Professor </option>
            <option name="ta" value="ta">TA </option>
            <option name="committee" value="committee">Committee </option>
            <option name="sac" value="sac">SAC </option>
        </select>

        <label>User Name </label>
        <input type="text" id="username" name="username" class="form-control"  placeholder="" required="true">

        <label>Name </label>
        <input type="text" id="name" name="name" class="form-control" placeholder="" required="true">

        <label>Email address</label>
        <input type="email" id="email" name="email" class="form-control" placeholder=""  required>
        <small class="form-text text-muted">We'll never share your email with anyone else.</small>
        <br>
        <label>Create password</label>
        <input class="form-control" id="password" style="border-radius: 5px 5px 5px 5px" name="password" type="password" minlength="4" required>

        <label>Confirm password</label>
        <input class="form-control" id="pass" name="pass" style="border-radius: 5px 5px 5px 5px" type="password" minlength="4" required>
        <p id ="pass-match" style="color:crimson ; font-family: Arial" ></p>

        <button class="btn btn-info btn-block login" type="submit">Submit</button>
    </form>
</div>
</div>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
                    <script>
                        $('#password, #pass').on('keyup', function () {
                            if ($('#password').val() == $('#pass').val()) {
                                $('#pass-match').html('Passwords match :D').css('color', 'green').css( 'font-family' , 'Ubuntu');
                            } else
                                $('#pass-match').html('Passwords dont match').css('color', 'red').css( 'font-family' , 'Ubuntu');

                        });
                    </script>
<%--                    <script type="text/javascript">--%>
<%--                        function Validate() {--%>
<%--                            var password = document.getElementById("password").value;--%>
<%--                            var confirmPassword = document.getElementById("pass").value;--%>
<%--                            if (password != confirmPassword) {--%>
<%--                                alert("Passwords do not match.");--%>
<%--                                return false;--%>
<%--                            }--%>
<%--                            return true;--%>
<%--                        }--%>
<%--                    </script>--%>






        <%--<div class="row justify-content-center" >--%>
<%--    <div class="col-md-6" align="center">--%>
<%--        <div class="card" style="margin:auto; width:max-content">--%>

<%--            <article class="card-body">--%>
<%--                <form class ="form-signin" id="admission-form" action = "/register" method="post"  onsubmit="return Validate()" />--%>
<%--                </td>--%>
<%--                <div class="form-row">--%>

<%--                    <div class="col form-group">--%>
<%--                        <label>User Type </label>--%>
<%--                        <select class="form-control" id="usertype" name="usertype" required>--%>
<%--                            <option name="select_usertype" value="">Select User Type</option>--%>
<%--                            <option name="professor" value="professor">Professor </option>--%>
<%--                            <option name="ta" value="ta">TA </option>--%>
<%--                            <option name="committee" value="committee">Committee </option>--%>
<%--                            <option name="sac" value="sac">SAC </option>--%>
<%--                        </select>--%>
<%--                    </div>--%>
<%--                    <div class="col form-group">--%>
<%--                        <label>User Name </label>--%>
<%--                        <input type="text" id="username" name="username" class="form-control" placeholder="" required="true">--%>
<%--                    </div>--%>
<%--                    <div class="col form-group">--%>
<%--                        <label>Name </label>--%>
<%--                        <input type="text" id="name" name="name" class="form-control" placeholder="" required="true">--%>
<%--                    </div> <!-- form-group end.// -->--%>
<%--                    <!-- form-group end.// -->--%>
<%--                </div> <!-- form-row end.// -->--%>


<%--                <div class="form-group">--%>
<%--                    <label>Email address</label>--%>
<%--                    <input type="email" id="email" name="email" class="form-control" placeholder=""  required>--%>
<%--                    <small class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
<%--                </div> <!-- form-group end.// -->--%>


<%--                <div class="form-group">--%>
<%--                    <label>Create password</label>--%>
<%--                    <input class="form-control" id="password" name="password" type="password" minlength="4" required>--%>
<%--                </div> <!-- form-group end.// -->--%>
<%--                <div class="form-group">--%>
<%--                    <label>Confirm password</label>--%>
<%--                    <input class="form-control" id="pass" name="pass" type="password" minlength="4" required>--%>
<%--                </div>--%>
<%--                <p id ="pass-match" style="color:crimson ; font-family: Arial" ></p>--%>

<%--                <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>--%>
<%--                <script>--%>
<%--                    $('#password, #pass').on('keyup', function () {--%>
<%--                        if ($('#password').val() == $('#pass').val()) {--%>
<%--                            $('#pass-match').html('Passwords match :D').css('color', 'green');--%>
<%--                        } else--%>
<%--                            $('#pass-match').html('Passwords dont match').css('color', 'red');--%>

<%--                    });--%>
<%--                </script>--%>
<%--                <script type="text/javascript">--%>
<%--                    function Validate() {--%>
<%--                        var password = document.getElementById("password").value;--%>
<%--                        var confirmPassword = document.getElementById("pass").value;--%>
<%--                        if (password != confirmPassword) {--%>
<%--                            alert("Passwords do not match.");--%>
<%--                            return false;--%>
<%--                        }--%>
<%--                        return true;--%>
<%--                    }--%>
<%--                </script>--%>


<%--                <div class="form-group">--%>
<%--                    <button id="sub-btn" type="submit" class="btn btn-primary btn-block"> Register  </button>--%>
<%--                </div>--%>
<%--                <small class="text-muted">By clicking the 'Register' button, you confirm that you accept our <br> Terms of use and Privacy Policy.</small>--%>
<%--                </form>--%>
<%--            </article>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--</div>--%>

<br>


</body>
</html>




