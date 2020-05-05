
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
<head>

    <style>
        .error
        {
            color: #ff0000;
            font-weight: bold;
        }
    </style>

    <title>Seller Registration</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
    <link rel="stylesheet" href="../../css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="../../css/main.css">
    <script src="../../js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

</head>


<body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                   <a class="navbar-brand" href="#" style="color:gray ; font-size: 18px">Class Room Management System</a>
            </div>
        </div>
    </nav>
<br>


    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">

                <article class="card-body">
                    <form class ="form-signin" id="admission-form" action = "/register/professor" method="post"  onsubmit="return Validate()" />
                    </td>
                        <div class="form-row">

                            <div class="col form-group">
                                <label>User Type </label>
                                <select class="form-control" id="usertype" name="usertype" required>
                                    <option name="select_username" value="">Select User Type</option>
                                    <option name="admin" value="admin">Admin </option>
                                    <option name="professor" value="professor">Professor </option>
                                    <option name="ta" value="ta">TA </option>
                                    <option name="committee" value="committee">Committee </option>
                                    <option name="sac" value="sac">SAC </option>
                                </select>
                            </div>
                            <div class="col form-group">
                                <label>User Name </label>
                                <input type="text" id="username" name="username" class="form-control" placeholder="" required="true">
                            </div>
                            <div class="col form-group">
                                <label>Name </label>
                                <input type="text" id="name" name="name" class="form-control" placeholder="" required="true">
                            </div> <!-- form-group end.// -->
                            <!-- form-group end.// -->
                        </div> <!-- form-row end.// -->


                        <div class="form-group">
                            <label>Email address</label>
                            <input type="email" id="email" name="email" class="form-control" placeholder=""  required>
                            <small class="form-text text-muted">We'll never share your email with anyone else.</small>
                        </div> <!-- form-group end.// -->


                        <div class="form-group">
                            <label>Create password</label>
                            <input class="form-control" id="password" name="password" type="password" minlength="4" required>
                        </div> <!-- form-group end.// -->
                        <div class="form-group">
                            <label>Confirm password</label>
                            <input class="form-control" id="pass" name="pass" type="password" minlength="4" required>
                        </div>
                    <p id ="pass-match" style="color:crimson ; font-family: Arial" ></p>

                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
                    <script>
                        $('#password, #pass').on('keyup', function () {
                        if ($('#password').val() == $('#pass').val()) {
                        $('#pass-match').html('Passwords match :D').css('color', 'green');
                        } else
                        $('#pass-match').html('Passwords dont match').css('color', 'red');

                        });
                    </script>
                    <script type="text/javascript">
                            function Validate() {
                                var password = document.getElementById("password").value;
                                var confirmPassword = document.getElementById("pass").value;
                                if (password != confirmPassword) {
                                    alert("Passwords do not match.");
                                    return false;
                                }
                                return true;
                            }
                    </script>


                        <div class="form-group">
                            <button id="sub-btn" type="submit" class="btn btn-primary btn-block"> Register  </button>
                        </div>
                        <small class="text-muted">By clicking the 'Register' button, you confirm that you accept our <br> Terms of use and Privacy Policy.</small>
                    </form>
                </article>
            </div>
        </div>

    </div>

<br><br>

</body>
</html>
