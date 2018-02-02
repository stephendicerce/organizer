package organizer

class UrlMappings {

    static mappings = {

        // Page url mapping
        "/"(view:"/application/landing")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
