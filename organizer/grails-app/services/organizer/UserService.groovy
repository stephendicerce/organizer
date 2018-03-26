package organizer

import grails.gorm.transactions.Transactional
import org.springframework.http.HttpStatus
import util.Pair
import util.QueryResult
import util.RoleType

@Transactional
class UserService {

    class UserErrors {
        final static String INVALID_ACCESS_TOKEN = "Access token invalid"
        final static String USER_NOT_FOUND = "User not found with given token"
    }

    boolean checkIfOrganizationAdmin(User user) {user.role.type == RoleType.ORGANIZATIONADMIN}

    QueryResult<User> getUser(String token) {
        QueryResult queryResult = new QueryResult()
        queryResult.success = false
        AuthToken authToken = AuthToken.findByAccessToken(token)
        if(authToken == null) {
            queryResult.message = UserErrors.INVALID_ACCESS_TOKEN
            queryResult.errorCode = HttpStatus.BAD_REQUEST.value()
            return queryResult
        }

        User user = authToken.user

        if(user == null) {
            queryResult.message = UserErrors.USER_NOT_FOUND
            queryResult.errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value()
            return queryResult
        }
        queryResult.data = user
        queryResult.success = true
        return queryResult
    }

    User getOrMakeByEmail(String email) {
        User user = User.findByEmail(email)

        if(user == null) {
            user = new User(email: email)
            user.setRole(new Role(type: RoleType.USER, master: RoleType.USER))
            user.save(flush: true, failOnError: true)
        }
        return user
    }

    Optional<Pair<User, AuthToken>> getMakeOrUpdate(String subj, String first, String last,
                                                    String imageUrl, String email) {
        User user
        AuthToken token
        Calendar calendar

        token = AuthToken.findBySubject(subj)

        // we have found an auth object. Therefore we have this user and they've signed in before.
        if (token != null) {
            println "user located"
            user = token.user
            calendar = user.calendar
            if (user.email != email) {
                user.email = email
            }
            token.accessToken = UUID.randomUUID()
            token.save(flush: true, failOnError: true)
        } else {
            //println "new or pre-loaded user"
            // two possible situations here. This is a new user or it's a pre-loaded user
            //signing in for the first time.

            user = User.findByEmail(email)
            if (user == null) { //it's a new user
                println "new user"
                calendar = new Calendar()
                user = new User(firstName: first, lastName: last, imageUrl: imageUrl, email: email, calendar: calendar)
                user.setRole(new Role(type: RoleType.USER, master: RoleType.USER))
            } else {
                println "no user found."
            }
        }
        if(calendar != null) {
            user = user.save(flush: true, failOnError: true)
            calendar.save(flush: true, failOnError: true)
        }
        if (user.authToken == null) {
            user.setAuthToken(new AuthToken(subject: subj, accessToken: UUID.randomUUID()))
            user = user.save(flush: true, failOnError: true)
        }

        token = user.authToken

        user != null ? Optional.of(new Pair<User, AuthToken>(user, token))
                : Optional.empty()

    }

    QueryResult<List<User>> findUsersBy(AuthToken token, String first, String last, String email) {
        QueryResult<List<User>> result


        def users = User.createCriteria().list {
            if (first) {
                like('firstName', first.concat("%"))
            }

            if (last) {
                like('lastName', last.concat("%"))
            }

            if (email) {
                eq('email', email.concat("%"))
            }
        } as List<User>

        result = new QueryResult<>(success: true, data: users)

        result
    }

    QueryResult<User> createUser(String email, String role) {
        QueryResult<User> result
        if(User.findByEmail(email) == null) {
            RoleType roleType
            if(role != null) {
                try {
                    roleType = role as RoleType
                } catch (IllegalArgumentException e) {
                    return new QueryResult<>(success: false, errorCode: HttpStatus.BAD_REQUEST.value(), message: "Unexpected role:" + role)
                }
            } else {
                roleType = RoleType.USER
            }

            User temp = new User(email: email)
            temp.setRole(new Role(type: roleType, master: roleType))
            temp.save(flush: true)
            result = new QueryResult<>(success: true, data: temp)
        } else {
            result = new QueryResult<>(success: false, errorCode: HttpStatus.BAD_REQUEST.value(), message: "Email already exists")

        }
        result
    }

    QueryResult<User> findUser(AuthToken token) {
        QueryResult<User> result = new QueryResult<>()
        User user = token?.user
        if(!user) {
            return QueryResult.fromHttpStatus(HttpStatus.BAD_REQUEST)
        }
        result.data = user
        result
    }
}