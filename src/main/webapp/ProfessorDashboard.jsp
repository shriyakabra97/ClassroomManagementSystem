<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Request Class</title>
    <!--    <link rel="icon" href="https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200X200.png"-->
    <!--          type = "image/x-icon">-->
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="/css/index.css">
    <style>
        body {
            background: whitesmoke;
        }
        #purpose, #building, #times , #times-label, #cleaningCheck , #plugsAndPorts, #capacity, #date, h5{
            margin-bottom: 10px;
        }
        nav a{
            color: white;
        }

    </style>
</head>
<body>
<nav class="navbar fixed-top navbar-light" style="background-color: #50c7e5; ">
    <!-- Navbar content -->
    <a class="navbar-brand" href="#">IIIT-B Clasroom Manager</a>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="#" style="margin-right: 10px"><span class="glyphicon glyphicon-log-in" ></span> Logout</a></li>
    </ul>
</nav>

<div class="container">
    <div class="login-container" style="width: 500px; margin: 30px auto">
        <div id="output"></div>
        <h4>Hey Prof ${username}!</h4><br>
        <div class="form-box">
            <form action="" method="">
                <select id="purpose" name="purpose" required>
                    <option name="select_username" value="">Select Purpose</option>
                    <option name="extraClass" value="extraClass">Extra Class </option>
                    <option name="exams" value="exams">Exams </option>
                    <option name="event" value="event">Event </option>
                    <option name="interactionWithCommittee" value="interactionWithCommittee">Interaction with committee </option>
                </select>

                <select id="building" name="building" required>
                    <option name="select_building" value="">Select Building</option>
                    <option name="aryabhatta" value="aryabhatta">Aryabhatta</option>
                    <option name="ramanujan" value="ramanujan">Ramanujan</option>
                </select>

                <input id="capacity" name="capacity" type="number" placeholder="Capacity" required >

                <h5 id="date-label" align="left">Select Date</h5>
                <input id="date" name="date" type="date" required placeholder="Date">

                <h5 id="times-label" align="left">Select Start and End Time</h5>
                <div class="row" id="times">
                    <div class="col-sm-6">
                        <input name="startTime" type="time" placeholder="Start Time" required >
                    </div>
                    <div class="col-sm-6">
                        <input name="endTime" type="time" required placeholder="End Time">
                    </div>
                </div>
                <h5 id="plugsAndPorts-label" align="left" style="margin-top: 5px">Concerned about plugs, ports and projectors?</h5>
                <div class="row" id="plugsAndPorts">
                    <div class="col-sm-4">
                        <input name="plugs" type="number" placeholder="Plugs" >
                    </div>
                    <div class="col-sm-4">
                        <input name="ports" type="number" placeholder="Ports" >
                    </div>
                    <div class="col-sm-4">
                        <input name="projectors" type="number" placeholder="Projectors" >
                    </div>
                </div>
                <div id="cleaningCheck" class="form-group">
                    <div class="form-check" align="left">
                        <input class="form-check-input" type="checkbox" id="gridCheck" style="width: 15px; height: 15px; ">
                        <label class="form-check-label" for="gridCheck" style="font-size: small">
                            Cleaning needed
                        </label>
                    </div>
                </div>

                <button class="btn btn-info btn-block login" type="submit">Find Classes</button>
            </form>
        </div>
    </div>

</div>
</body>
</html>