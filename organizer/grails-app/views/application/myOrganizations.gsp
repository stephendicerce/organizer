<!DOCTYPE html>
<html>
<head>
    <title>My Events</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="google-signin-client_id" content="578019460076-9h535196sj93sernlo2l09njo22fcn2e.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <asset:javascript src="auth/config.js"/>
    <asset:javascript src="auth/logout.js"/>
    <asset:stylesheet src="calendarView.css"/>
    <asset:stylesheet src="menuButton.css"/>
    <asset:javascript src="menuButton.js"/>
    <asset:javascript src="myOrganizations.js"/>
</head>

<body>
<div class="intro-header">
    <div>
        <div id="headerGrad">
            <div id="title">
                My Organizations
            </div>
            <span class="otherTitleContent" style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
            <br><br><br><br><br><br><br>
            <div class="otherTitleContent">
                <button type="button" onclick="document.location.href='../dashboard'">
                    <span class="sr-only">Toggle navigation</span> Dashboard <i class="fa fa-bars"></i>
                </button>
                <button type="button" onclick="logout()">
                    <span class="sr-only">Toggle navigation</span> LOGOUT <i class="fa fa-bars"></i>
                </button>
            </div>
            <div id="mySidenav" class="sidenav">
                <div content="menuContentHTML"></div>
            </div>
        </div>
        <div class="container">

            <div onclick="getAllOrganizations()">
                Click here to view your organizations:
            </div>
            <div id="orgList"></div>
        </div>
    </div>
</div>
</body>
</html>