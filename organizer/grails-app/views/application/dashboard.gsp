<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="google-signin-client_id" content="578019460076-9h535196sj93sernlo2l09njo22fcn2e.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <asset:javascript src="auth/config.js"/>
    <asset:javascript src="auth/logout.js"/>

</head>
<title>Your Calendar!</title>
<body class="bg-light-gray">
<div class="intro-header">
    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <div class="intro-message">
                    <h1>Dashboard</h1>
                    <button type="button" onclick="logout()">
                        <span class="sr-only">Toggle navigation</span> LOGOUT <i class="fa fa-bars"></i>
                    </button>
                    <button type="button" onclick="newEvent()">
                        <span class="sr-only">Toggle navigation</span> NEW EVENT <i class="fa fa-bars"></i>
                    </button>
                    <button type="button" onclick="createOrganization()">
                        <span class="sr-only">Toggle navigation</span> CREATE ORGANIZATION <i class="fa fa-bars"></i>
                    </button>
                    <h3></h3>
                </div>
                <div style="width:100px;height:25px;border:1px solid #000;"></div>
                <div style="width:100px;height:100px;border:1px solid #000;"></div>
                <div style="width:100px;height:100px;border:1px solid #000;"></div>
                <div style="width:100px;height:100px;border:1px solid #000;"></div>
                <div style="width:100px;height:100px;border:1px solid #000;"></div>
                <div style="width:100px;height:100px;border:1px solid #000;"></div>
            </div>
        </div>
    </div>
</div>
</body>


</html>