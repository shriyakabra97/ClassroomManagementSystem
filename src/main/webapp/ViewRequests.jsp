<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.spe.ClassroomManagementSystem.Models.TA" %>
<%@ page import="org.springframework.http.ResponseEntity" %>
<%@ page import="org.springframework.web.bind.annotation.RequestMapping" %>
<%@ page import="org.springframework.boot.web.servlet.server.Session" %>
<%@ page import="java.util.List" %>
<%@ page import="com.spe.ClassroomManagementSystem.Service.ReturnableRequest" %>
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
            background: url("/images/reduced_opacity_bg.jpeg");
            background-repeat:repeat-x;

        }
        nav a{ color: white;
        }

    </style>



</head>
<body>
<%
    if(session.getAttribute("admin_login")!=null){
%>
<script type="text/javascript">
    <%
     if (session.getAttribute("view_req_msg")!=null){
     %>
    alert('<%= session.getAttribute("view_req_msg")%>');
    <%
    session.setAttribute("view_req_msg",null);
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
        <li><a href="/getAllClassrooms">Add Timetable</a> </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/destroy" style="margin-right: 10px"><span class="glyphicon glyphicon-log-in" ></span> Logout</a></li>
    </ul>
</nav>

<br>
<%
    List<ReturnableRequest> returnableRequestList = (List<ReturnableRequest>) session.getAttribute("returnableRequestList");
    if (returnableRequestList.size() >0){
%>

<div class="container" >
    <br><br>

    <h2 >Hey, here are the requests...</h2>
    <h5 style="color:darkblue">Request can either be Granted or Rejected.</h5>

    <br>
    <table class="table table-hover table-responsive table-bordered" style="background: white">
        <thead style="background-color: #cccccc">
        <tr>
            <th scope="col">Date</th>
            <th scope="col">From</th>
            <th scope="col">To</th>
            <th scope="col">Classroom</th>
            <th scope="col">Requestor</th>
            <th scope="col">Purpose</th>
            <th scope="col">Projector required</th>
            <th scope="col">Cleaning required</th>
            <th scope="col">Plugs</th>
            <th scope="col">Comment</th>
            <th scope="col">Grant</th>
            <th scope="col">Reject</th>
        </tr>
        </thead>
        <tbody>
        <br>

        <c:forEach var="e" items="${returnableRequestList}">
            <tr>
                <td>${e.classRequestDate}</td>
                <td>${e.startTime}</td>
                <td>${e.endTime}</td>
                <td>${e.classCode}</td>
                <td>${e.rName}</td>
                <td>${e.purpose}</td>
                <td>${e.projector}</td>
                <td>${e.cleaningRequired}</td>
                <td>${e.plugs}</td>
                <td>${e.comment}</td>
                <td><a href="/acceptRequest/${e.index}/${e.loginId}/${e.classroomId}/${e.requestId}/${e.classRequestDate}/${e.startTime}/${e.endTime}" class="btn btn-success">GRANT</a></td>
                <td><a href="/rejectRequest/${e.index}/${e.loginId}/${e.classroomId}/${e.requestId}" class="btn btn-danger">REJECT</a></td>

            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>

<% }else{        %>
<br><br><br>
<div class="container">
    <h3 align="center"> No pending Requests..</h3>

</div>


<% } %>


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