package organizer

class UrlMappings {

    static mappings = {

        // Page url mapping
        "/"(controller: 'application', action: 'landing')
        "/dashboard"(controller: 'application', action: "dashboard")


        "/user/auth"(controller: 'auth', action: 'auth', method: 'post')
        "/user/auth"(controller: 'auth', action: 'current', method: 'get')
        "/user/logout"(controller: 'auth', action: 'logout', method: 'post')









        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
