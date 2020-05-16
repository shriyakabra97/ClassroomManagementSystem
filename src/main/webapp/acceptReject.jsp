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
        .error{
            color: #57d3f2;
        }

    </style>



</head>
<body>
<nav class="navbar navbar-fixed-top navbar-light" style="background-color: #563D7C; ">
    <!-- Navbar content -->
    <a class="navbar-brand" href="#">IIIT-B Clasroom Manager</a>

</nav>

<div class="container">
    <div class="error">
        <br>
        <br>
        <br>
        <h2> <% if(session.getAttribute("rejectMsg")!=null)
              out.println(session.getAttribute("rejectMsg")); %>
        </h2>
        <h2> <% if(session.getAttribute("acceptMsg")!=null)
            out.println(session.getAttribute("acceptMsg")); %>
        </h2>
        <br>
        <a href="/getAllRequests">Click here to View the Requests Again</a>
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
</body