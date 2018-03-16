function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {

        alert("You have successfully logged out!");
        $(".g-signin2").css("display", "block");
        $(".data").css("display", "none")
    });
}