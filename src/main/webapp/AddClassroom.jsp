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
    <link rel="stylesheet" href="/css/All.css">
    <script src="/js/AddClassroom.js"></script>

    <style>
        body{
            font-family: Ubuntu;
            /*background: rgba(181,123,229,0.16);*/
            background: url("/images/reduced_opacity_bg.jpeg");

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
     if (session.getAttribute("class_save_msg")!=null){
     %>
    alert('<%= session.getAttribute("class_save_msg")%>');
    <%
    session.removeAttribute("class_save_message");
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

<div class="container">
    <div class="login-container" style="width: 500px; margin: 30px auto">
        <div id="output"></div>
        <h5>Add Classroom</h5>
        <div class="form-box">
            <form  id="addclass-form" action="/classroom" method="">
                <div class="form-group">
                    <input id="classCode" name="classCode" type="text" placeholder="Class Name" >
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
                            <input id="plugs" name="plugs" type="number" placeholder="Plugs" >
                        </div>

                    </div>
                </div>
                <br>
                <div id="projCheck" class="form-group">
                    <div class="form-check" align="left">
                        <input class="form-check-input" type="checkbox" id="projector" name="projector" style="width: 15px; height: 15px; ">
                        <label class="form-check-label" for="projector" style="font-size: small">
                            Projector available
                        </label>
                    </div>
                </div>

                <button class="btn  btn-secondary btn-block login mybutton"  type="submit">Add Classroom</button>
<%--                <p>${class_save_msg}</p>--%>
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/additional-methods.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/additional-methods.js"></script>

<% }
else {
    response.sendRedirect("LoginFirst.jsp");
}
%>

</body>
</html>