/*
function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    $(".g-signin2").css("display", "none");
    $(".data").css("display", "block");
    $("#pic").attr('src',profile.getImageUrl());
    $("#email").text(profile.getEmail());
    var id_token = googleUser.getAuthResponse().id_token;

    var xhr = new XMLHttpRequest();
    xhr.open('POST', )


    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
}

*/

$(document).ready(function () {

    function signOut() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut();
    }

    function authSuccess(data) {
        console.log(data);
        if (data.status) {
            document.location.href = "/dashboard";
        } else {
            signOut();
        }
    }

    function authFailure(xhr, textStatus, errorThrown) {
        var responseText = xhr.responseText;
        if(responseText != null) {
            var status;
            try {
                status = JSON.parse(responseText);
            }
            catch(e) {
                status = undefined;
            }

            if(status && status.message) {
                alert(status.message);
            }
        }
        signOut();
    }

    function send(token) {
        $.ajax({
            url: '/user/auth',
            method: "POST",
            data: {
                idToken: token
            },
            success: authSuccess,
            error: authFailure
        });
    }

    function renderButton() {
        gapi.signin2.render('sign-in-btn', {
            'width': 240,
            'height': 50,
            'longtitle': true,
            'theme': 'dark'
        });
    }

    gapi.load('auth2', function () {
        var auth2 = gapi.auth2.init({
            client_id: _clientid,
            scope: 'profile'
        });

        auth2.currentUser.listen(function (user) {
            if (auth2.isSignedIn.get() == true) {
                var token = user.getAuthResponse().id_token;
                send(token);
            }
        });

        renderButton();

    });
});

