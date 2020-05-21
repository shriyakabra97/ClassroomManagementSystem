<%@ page import="java.util.List" %>
<%@ page import="com.spe.ClassroomManagementSystem.Models.Classroom" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Available Classrooms</title>
    <link rel="stylesheet" href="/css/All.css">

    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <style>
        body{
            font-family: Ubuntu;
            background: url("/images/reduced_opacity_bg.jpeg");

        }
        nav a{ color: white;
        }

        .b{
            background-color: darkgray;
            color: white;
            padding: 10px 30px 10px 30px;
        }
        .b:hover{
            background-color: #999999;
            color: white;
            padding: 10px 30px 10px 30px;

        }
        a, a:hover{
            text-decoration: none;
            color: white;
        }
        a:hover{
            text-decoration: none;
            color: white;
            background-color: #999999;
        }

    </style>
    <link rel="stylesheet" href="/css/All.css">

</head>
<body>
<%

    if(session.getAttribute("login")!=null){
%>
<nav class="navbar navbar-fixed-top navbar-light" style="background-color: #563D7C; ">
    <!-- Navbar content -->
    <a class="navbar-brand" href="#">IIIT-B Clasroom Manager</a>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/destroy" style="margin-right: 10px"><span class="glyphicon glyphicon-log-in" ></span> Logout</a></li>
    </ul>
</nav>
<%
    List<Classroom> classroomList = (List<Classroom>) session.getAttribute("availableClassrooms");
    if (classroomList.size() > 0){
%>
<div class="container">
    <h4>Hey, here are the available classrooms..</h4>
    <table class="table table-hover table-bordered table-responsive" style="background: white">
        <thead style="background:rgba(85,85,85,0.33)">
        <tr>
            <th scope="col">Classroom</th>
            <th scope="col">Capacity</th>
            <th scope="col">Projectors</th>
            <th scope="col">Plugs</th>
            <th scope="col">Request</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="e" items="${availableClassrooms}">
        <tr>
            <td>${e.classCode}</td>
            <td>${e.capacity}</td>
            <td>${e.projector}</td>
            <td>${e.plugs}</td>
            <td><a href="/postRequest/${e.classCode}" class="btn btn-success">Request this room</a></td>
        </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
<%
    }else {
%>

<div class="container text-center">
    <h3 align="center">No Classrooms available for your choice</h3>
    <h4 align="center">Try Again!</h4>
    <% if (session.getAttribute("userType").equals("professor")){%>
    <button class="btn b"> <a href="/ProfessorDashboard.jsp">Return to Dashboard</a></button>
    <% } else if (session.getAttribute("userType").equals("sac")){%>
    <button class="btn b"> <a href="/SACDashboard.jsp">Return to Dashboard</a></button>
    <% } else if (session.getAttribute("userType").equals("committee")){%>
    <button class="btn b"> <a href="/CommitteeDashboard.jsp">Return to Dashboard</a></button>
    <% } else if (session.getAttribute("userType").equals("ta")){%>
    <button class="btn b"> <a href="/TADashboard.jsp">Return to Dashboard</a></button>
    <% } %>
</div>

<%
    }
%>

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
</body>
<% }
else {
    response.sendRedirect("LoginFirst.jsp");
}
%>
</html>