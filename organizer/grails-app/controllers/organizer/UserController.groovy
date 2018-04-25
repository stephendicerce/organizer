package organizer

import org.springframework.http.HttpStatus
import util.QueryResult

class UserController {

    static responseFormats = ['json', 'xml']

    PreconditionService preconditionService
    UserService userService

    def getUser(String accessToken, String first_name, String last_name, String email) {
        QueryResult<AuthToken> checks = new QueryResult<>()
        preconditionService.notNull(params, ['accessToken'], checks)
        preconditionService.accessToken(accessToken, checks)

        if(checks.success) {
            if(first_name || last_name || email) {
                QueryResult<List<User>> queryResult = userService.findUsersBy(first_name, last_name, email)
                if(queryResult.success) {
                    render(view: 'users', model: [token: checks.data, users: queryResult.data])
                } else {
                    render(view:'../failure', model: [errorCode: queryResult.errorCode, message: queryResult.message])
                }
            } else {
                render(view:'../failure', model:[errorCode: HttpStatus.BAD_REQUEST.value(), message: 'Must Specify at least one search parameter'])
            }
        } else {
            render(view: '../failure', model: [errorCode: checks.errorCode, message:  checks.message])
        }
    }

    def postUser(String accessToken, String email) {
        QueryResult<AuthToken> checks = new QueryResult<>()
        preconditionService.notNull(params, ['accessToken', 'email'], checks)
        preconditionService.accessToken(accessToken, checks)

        if(checks.success) {
            QueryResult<User> result = userService.createUser(email)
            if(result.success) {
                render(view: 'users', model: [token: checks.data, users: [result.data]])
            } else {
                render(view: '../failure', model: [errorCode: result.errorCode, message: result.message])
            }
        } else {
            render(view:  '../failure', model: [errorCode:  checks.errorCode, message: checks.message])
        }
    }


}
