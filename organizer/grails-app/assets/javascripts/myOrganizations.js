var userOrgs = [];
var token = '';


function getAllOrganizations() {
    $.ajax({
        url: '/user/auth',
        method: 'GET',

        success: function (data) {
            token = data.data.token;

            var urlString = 'api/organization/user/all?accessToken='+token;
            $.ajax({
                url: urlString,
                method: 'GET',

                success: function (data) {
                    userOrgs = data.data.organizations;

                    var i;
                    var orgsDiv = document.getElementById("orgList");
                    if(userOrgs.length === 0) {
                        var string = "<p style=\"text-align: center;\">You currently don't belong to any organizations.</p>";
                        var div = document.createElement("div");
                        div.innerHTML = string;
                        orgsDiv.appendChild(div);
                    }

                    for (i = 0; i < userOrgs.length; i++) {
                        var description;
                        if(userOrgs[i].orgDescription === null){
                            description = "This organization has no description"
                        } else {
                            description = userOrgs[i].orgDescription
                        }
                        console.log(userOrgs[i].orgId);
                        var htmlString = orgHTML(userOrgs[i].orgName, description, userOrgs[i].members, userOrgs[i].orgId);
                        var newDiv = document.createElement("div");
                        newDiv.innerHTML = htmlString;
                        orgsDiv.appendChild(newDiv);
                    }
                }
            });
        }
    });
}


function orgHTML(orgName, description, members, deleteId) {
    //var str = '<div class="col-md-4 col-sm-6 portfolio-item" style="box-shadow: 0px 0px 0px gray; padding: 20px;">'
    var str = '<div class="col-md-4 col-sm-6 portfolio-item" style="box-shadow: 5px 5px 30px gray; padding: 10px;">';
    //var str = '<div class="col-md-4 col-sm-6 portfolio-item" style="box-shadow: 0px 0px 0px gray; padding: 10px;">'
    str += '<div class="portfolio-hover">';
    str += '<div class="portfolio-hover-content">';
    str += '<i class="fa fa-plus fa-3x"></i></div></div>';
    //str += '<asset:image class="img-responsive" src="logo.png" alt=""/>'
    //str += '<img src="assets/images/startup-framework.png" class="img-responsive" alt=""></a>'
    str += '<div class="portfolio-caption"><h4>' +orgName +'</h4><p class="text-muted"> Description: '+ description + '<br>Number of Members: '+members+'</p>' +
        '<button type="button" onclick="editOrg('+deleteId+')">\n' +
        '<span class="sr-only">Toggle navigation</span> Edit Info <i class="fa fa-bars"></i>\n' +
        '</button>&nbsp;' +
        '<button type="button" onclick="addUser('+deleteId+')">\n' +
        '<span class="sr-only">Toggle navigation</span> Add User to Organization <i class="fa fa-bars"></i>\n' +
        '</button>&nbsp;' +
        '<button type="button" onclick="deleteOrg('+deleteId+')">' +
        '<span class="sr-only">Toggle navigation</span> Delete Organization <i class="fa fa-bars"></i>' +
        '</button></div></div>';
    return str
}

function deleteOrg(orgId) {
    console.log(orgId);
    $.ajax({
        url: '/user/auth',
        method: "GET",

        success: function (data) {
            token = data.data.token;
            console.log(token);
            $.ajax({
                url: 'api/organization?accessToken=' + token + '&organizationId=' + orgId,
                method: "DELETE",
                success: function () {
                    window.location.reload()
                },
                error: function () {
                    alert("Organization could not be deleted, please try again");
                    getAllEvents()
                }
            });
        }
    });
}