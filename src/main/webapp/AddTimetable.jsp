<%@ page import="com.spe.ClassroomManagementSystem.Models.TA" %>
<%@ page import="org.springframework.http.ResponseEntity" %>
<%@ page import="org.springframework.web.bind.annotation.RequestMapping" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page import="org.springframework.web.bind.annotation.RequestBody" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> Add Timetable</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/index.css">
    <script src="/js/AddTimetable.js"></script>

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
        <li><a href="/getAllClassrooms">Add Timetable</a> </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/destroy" style="margin-right: 10px"><span class="glyphicon glyphicon-log-in" ></span> Logout</a></li>
    </ul>
</nav>

<div class="container">
    <div class="login-container" style="width: 500px; margin: 30px auto">
        <h5>Add Timetable</h5>
        <div id="output"></div>

        <div class="form-box">
            <form  id="addtimetable-form" action="/timetable" method="">
                <h5 id="class-label" align="left">Select Classroom</h5>

                <div class="form-group">
                    <select id="classCode" name="classCode" required="">
                        <option name="select_classname" value="">Select Class</option>
                        <c:forEach var="e" items="${classroomList}">
                            <option name="${e.classCode}" value="${e.classCode}" >${e.classCode}</option>
                        </c:forEach>
                    </select>
                </div>
                <h5 id="day-label" align="left">Select Day</h5>
                <div class="form-group">
                    <select id="day" name="day" required>
                        <option name="select_day" value="">Select Day</option>
                        <option name="monday" value="MONDAY">Monday</option>
                        <option name="tuesday" value="TUESDAY">Tuesday</option>
                        <option name="wednesday" value="WEDNESDAY">Wednesday</option>
                        <option name="thursday" value="THURSDAY">Thursday</option>
                        <option name="friday" value="FRIDAY">Friday</option>
                        <option name="saturday" value="SATURDAY">Saturday</option>
                        <option name="sunday" value="SUNDAY">Sunday</option>
                    </select>
                </div>
                <br>
                <h5 id="times-label" align="left">Select Start and End Time ( 24 hour format )</h5>
                <div class="row" id="times">
                    <div class="form-group">

                        <div class="col-sm-6">
                            <input id="startTime" name="startTime" type="time" placeholder="Start Time" required >
                        </div>
                        <div class="col-sm-6">
                            <input id="endTime" name="endTime" type="time" required placeholder="End Time">
                        </div>
                    </div>
                </div>


                <br>
                <button class="btn btn-info btn-block login" type="submit">Submit</button>
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