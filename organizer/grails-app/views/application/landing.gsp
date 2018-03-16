<!DOCTYPE html>
<html lang="en">
<head>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="google-signin-client_id" content="578019460076-9h535196sj93sernlo2l09njo22fcn2e.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <asset:javascript src="auth/config.js"/>
    <asset:javascript src="auth/login.js"/>
    <asset:javascript src="auth/logout.js"/>

    <style>

    .data{
        display: none;
    }
</style>


</head>
<title>Organizer</title>
<body class="bg-light-gray">
<div class="intro-header">
    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <div class="intro-message">
                    <h1>Sked</h1>
                    <h3>An everyday task manager to be used by you and your friends. Keep up to date with everyone's plans, as well as keeping on schedule with your own.</h3>
                    <div align="center" id="sign-in-btn"></div>
                    <div class="data">
                        <p>Profile Details</p>
                        <img id="pic" class="img-circle" width="100" height="100"/>
                        <p>Email Address</p>
                        <p id="email" class="alert alert-danger"></p>
                        <button onclick="signOut()" class="btn btn-danger">Sign Out</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>


</html>