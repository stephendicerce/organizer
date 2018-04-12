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

        group "/api/event", {
            "/user"(controller: 'event', action: 'getUserEvent', method: 'get')
            "/user"(controller: 'event', action: 'postUserEvent', method: 'post')
            "/"(controller: 'event', action: 'putUserEvent', method: 'put')
            "/"(controller: 'event', action: 'deleteEvent', method: 'delete')
        }









        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
