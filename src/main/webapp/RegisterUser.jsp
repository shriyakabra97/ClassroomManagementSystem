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
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/All.css">

    <link rel="stylesheet" href="/css/index.css">

    <script src="/js/RegisterUser.js"></script>
    <style>
        body{
            background: whitesmoke;
            background: url("/images/reduced_opacity_bg.jpeg");
            background-repeat:repeat-x;

        }
        nav a{ color: white;
        }
        form label{
            float: left;
        }

    </style>



</head>
<body>
<%
    if(session.getAttribute("admin_login")!=null){
%>
<script type="text/javascript">
    <%
     if (session.getAttribute("msg")!=null){
     %>
    alert('<%= session.getAttribute("msg")%>');
    <%
    session.removeAttribute("msg");
    %>
    <%
    }else {}
    %>
</script>
<nav class="navbar navbar-fixed-top navbar-light" style="background-color: #563D7C; ">
    <!-- Navbar content -->
    <a class="navbar-brand" href="AdminDashboard.jsp">IIIT-B Clasroom Manager</a>
    <ul class="nav navbar-nav navbar-left">
        <li><a href="RegisterUser.jsp"> Add User </a></li>
        <li><a href="/getAllRequests">View Requests</a></li>
        <li><a href="AddClassroom.jsp">Add Classroom</a> </li>
        <li><a href="AddTimetable.jsp">Add Timetable</a> </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/destroy" style="margin-right: 10px"><span class="glyphicon glyphicon-log-in" ></span> Logout</a></li>
    </ul>
</nav>

<br>
<div class="container">
<div class="login-container" style="width: 500px; margin: 30px auto">
    <h5>Register New User</h5>
<div class="form-box">
    <form action="/register" id="register-user-form" name="register-user-form" method="post">
        <label>User Type </label>
        <div class="form-group">
        <select class="form-control" id="usertype" name="usertype" required style="border-radius: 5px 5px 5px 5px">
            <option name="select_usertype" value="">Select User Type</option>
            <option name="professor" value="professor">Professor </option>
            <option name="ta" value="ta">TA </option>
            <option name="committee" value="committee">Committee </option>
            <option name="sac" value="sac">SAC </option>
        </select>
        </div>

        <br>

        <label>User Name </label>
        <div class="form-group">
        <input type="text" id="username" name="username" class="form-control" placeholder="" required="true">
        </div>
        <br>

        <label>Name </label>
        <div class="form-group">
        <input type="text" id="name" name="name" class="form-control" placeholder="" required="true">
        </div>

        <br>

        <label>Email address</label>
        <div class="form-group">
        <input type="email" id="email" name="email" class="form-control" placeholder=""  required>
        </div>

        <br>

        <label>Create password</label>
        <div class="form-group">
        <input class="form-control" id="password" style="border-radius: 5px 5px 5px 5px" name="password" type="password" minlength="4" required>
        </div>

        <br>

        <label>Confirm password</label>
        <div class="form-group">
        <input class="form-control" id="pass" name="pass" style="border-radius: 5px 5px 5px 5px" type="password" minlength="4" required><br>
        </div>

        <p id ="pass-match" style="color:crimson ; font-family: Arial" ></p>

        <button class="btn btn-secondary btn-block login mybutton" type="submit">Submit</button>
<%--        <p>${msg}</p>--%>
    </form>
</div>
</div>
</div>
<div>
    <!-- Footer -->
    <footer class="page-footer font-small blue">

        <!-- Copyright -->
        <div class="footer-copyright text-center py-3">2020 Copyright:
            <a>Students of IIIT-B</a>
        </div>
        <!-- Copyright -->

    </footer>
    <!-- Footer -->
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

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/additional-methods.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/additional-methods.js"></script>



<br>
<% }
else {
    response.sendRedirect("LoginFirst.jsp");
}
%>

</body>
</html>




