<%@ page import="com.spe.ClassroomManagementSystem.Models.TA" %>
<%@ page import="org.springframework.http.ResponseEntity" %>
<%@ page import="org.springframework.web.bind.annotation.RequestMapping" %>
<%--<%@ page import="org.springframework.web.bind.annotation.RequestBody" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> Add Classroom</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/index.css">
    <script src="/js/AddClassroom.js"></script>

    <style>
        body{
            font-family: Ubuntu;
            background: whitesmoke;
        }
        nav a{ color: white;
        }

    </style>


</head>
<body>
<nav class="navbar fixed-top navbar-light" style="background-color: #50c7e5; ">
    <!-- Navbar content -->
    <a class="navbar-brand" href="AdminDashboard.jsp">IIIT-B Clasroom Manager</a>
    <ul class="nav navbar-nav navbar-left">
        <li><a href="RegisterUser.jsp"> Add User </a></li>
        <li><a href="ViewRequests.jsp">View Requests</a></li>
        <li><a href="AddClassroom.jsp">Add Classroom</a> </li>
        <li><a href="AddTimetable.jsp">Add Timetable</a> </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/destroy" style="margin-right: 10px"><span class="glyphicon glyphicon-log-in" ></span> Logout</a></li>
    </ul>
</nav>

<div class="container">
    <div class="login-container" style="width: 500px; margin: 30px auto">
        <div id="output"></div>
        <h5>Add Classroom</h5>
        <div class="form-box">
            <form  id="addclass-form" action="" method="">
                <div class="form-group">
                    <input id="crName" name="crName" type="text" placeholder="Class Name">
                </div>

<%--                <div class="form-group">--%>
<%--                    <select id="building" name="building" required>--%>
<%--                        <option name="select_building" value="">Select Building</option>--%>
<%--                        <option name="aryabhatta" value="aryabhatta">Aryabhatta</option>--%>
<%--                        <option name="ramanujan" value="ramanujan">Ramanujan</option>--%>
<%--                    </select>--%>
<%--                </div>--%>
                <h5 id="capacity-label" align="left" style="margin-top: 5px">Capacity</h5>
                <div class="form-group">
                    <input id="capacity" name="capacity" type="number" placeholder="Capacity" value="50"  >
                </div>
                <br>
                <h5 id="plugsAndPorts-label" align="left" style="margin-top: 5px">Enter plugs and Projector details</h5>
                <div class="form-group">
                    <div class="row" id="plugsAndPorts">
                        <div class="col-sm-12">
                            <input name="plugs" type="number" placeholder="Plugs" >
                        </div>

                    </div>
                </div>
                <br>
                <div id="projCheck" class="form-group">
                    <div class="form-check" align="left">
                        <input class="form-check-input" type="checkbox" id="projectorCheck" style="width: 15px; height: 15px; ">
                        <label class="form-check-label" for="projectorCheck" style="font-size: small">
                            Projector available
                        </label>
                    </div>
                </div>

                <button class="btn btn-info btn-block login" type="submit">Add Classroom</button>
            </form>
        </div>
    </div>

</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/additional-methods.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/additional-methods.js"></script>

</body>
</html>