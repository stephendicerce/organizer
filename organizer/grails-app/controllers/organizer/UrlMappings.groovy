package organizer

class UrlMappings {

    static mappings = {

        // Page url mapping
        "/"(controller: 'application', action: 'landing')
        "/dashboard"(controller: 'application', action: "dashboard")


        "/user/auth"(controller: 'auth', action: 'auth', method: 'post')
        "/user/auth"(controller: 'auth', action: 'current', method: 'get')
        "/user/logout"(controller: 'auth', action: 'logout', method: 'post')

        group "/api/organization", {
            "/"(controller: 'organization', action: 'postOrganization', method: 'post')
            "/"(controller: 'organization', action: 'organizationGet', method: 'get')
            "/"(controller: 'organization', action: 'deleteOrganization', method: 'delete')
        }









        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
