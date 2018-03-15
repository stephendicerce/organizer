package organizer

import util.QueryResult

class ApplicationController {
    PreconditionService preconditionService

    def landing() {
        render(view: 'landing')
    }

    def dashboard() {
        //println "Doing dashboard stuff"
        QueryResult<AuthToken> access = hasAccess()
        if(access.success) {
            User user = access.data.user
            render(view: 'dashboard')
        } else {
            println "User isn't logged in"
            session.invalidate()
            render(view: 'landing')
        }
    }

    private QueryResult<AuthToken> hasAccess() {
        String access = session.getAttribute("access")
        preconditionService.accessToken(access)
    }
}
