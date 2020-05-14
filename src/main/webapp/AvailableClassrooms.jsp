<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Available Classrooms</title>
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

    if(session.getAttribute("login")!=null){
%>
<nav class="navbar fixed-top navbar-light" style="background-color: #50c7e5; ">
    <!-- Navbar content -->
    <a class="navbar-brand" href="#">IIIT-B Clasroom Manager</a>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/destroy" style="margin-right: 10px"><span class="glyphicon glyphicon-log-in" ></span> Logout</a></li>
    </ul>
</nav>

<div class="container">
    <h4>Hey, here are the available classrooms..</h4>
    <table class="table table-hover">
        <thead>
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
            <td><a href="/postRequest/${e.classCode}" class="btn btn-primary">Request this room</a></td>
        </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
<% }
else {
    response.sendRedirect("LoginFirst.jsp");
}
%>
</html>