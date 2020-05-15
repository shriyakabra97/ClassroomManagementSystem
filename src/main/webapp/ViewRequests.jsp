<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.spe.ClassroomManagementSystem.Models.TA" %>
<%@ page import="org.springframework.http.ResponseEntity" %>
<%@ page import="org.springframework.web.bind.annotation.RequestMapping" %>
<%--<%@ page import="org.springframework.web.bind.annotation.RequestBody" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="/css/All.css">

    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <style>
        body{
            font-family: Ubuntu;
        }
        nav a{ color: white;
        }

    </style>



</head>
<body>
<%
    if(session.getAttribute("admin_login")!=null){
%>
<nav class="navbar navbar-fixed-top navbar-light" style="background-color: #563D7C; ">
    <!-- Navbar content -->
    <a class="navbar-brand" href="AdminDashboard.jsp">IIIT-B Clasroom Manager</a>
    <ul class="nav navbar-nav navbar-left">
        <li><a href="RegisterUser.jsp"> Add User </a></li>
        <li><a href="/getAllRequests">View Requests</a></li>
        <li><a href="AddClassroom.jsp">Add Classroom</a> </li>
        <li><a href="/getAllClassrooms">Add Timetable</a> </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/destroy" style="margin-right: 10px"><span class="glyphicon glyphicon-log-in" ></span> Logout</a></li>
    </ul>
</nav>

<br>

<div class="container">
    <h4>Hey, here are the requests...</h4>
    <h5>Request can either be Granted or Rejected.</h5>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Date</th>
            <th scope="col">Start Time</th>
            <th scope="col">End Time</th>
            <th scope="col">Classroom</th>
            <th scope="col">Projector required</th>
            <th scope="col">Plugs</th>
            <th scope="col">Purpose</th>
            <th scope="col">Comment</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="e" items="${currentRequests}">
            <tr>
                <td>${e.classRequestDate}</td>
                <td>${e.startTime}</td>
                <td>${e.endTime}</td>
                <td>${e.projector}</td>
                <td>${e.plugs}</td>
                <td>${e.purpose}</td>
                <td>${e.comment}</td>

            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>





<br>
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
<% }
    else {
    response.sendRedirect("LoginFirst.jsp");
    }
%>
</body>
</html>