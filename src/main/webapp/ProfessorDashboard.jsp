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
            background: #eee url(http://subtlepatterns.com/patterns/sativa.png);
        }
    </style>
</head>
<body>

<div class="container">
    <div class="login-container" style="display: block">
        <div id="output"></div>
        <h4>Hey Prof ${profName}!</h4><br>
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

                <div class="row">
                    <div class="col-sm-6">
                        <input name="startTime" type="time" placeholder="Start Time" required style="width: 150px">
                    </div>
                    <div class="col-sm-6">
                        <input name="endTime" type="time" required placeholder="End Time" style="width: 150px">
                    </div>


                </div>

                <input name="endTime" type="time" required placeholder="End Time">
                <input name="user" type="" placeholder="username" required>
                <input type="password" placeholder="password" required>
                <button class="btn btn-info btn-block login" type="submit">Find Classes</button>
            </form>
        </div>
    </div>

</div>
</body>
</html>