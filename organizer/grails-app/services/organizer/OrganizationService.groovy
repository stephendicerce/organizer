
package organizer

import grails.gorm.transactions.Transactional
import org.springframework.http.HttpStatus
import util.QueryResult
import util.RoleType

@Transactional
class OrganizationService {
    UserService userService

    /*
    QueryResult<List<User>> getAllUsers(AuthToken token, String organizationId) {
        QueryResult<List<User>> res = new QueryResult<>()
        User requestingUser = token?.user
        long oid = organizationId.isLong() ? organizationId.toLong() : -1
        if(requestingUser != null && isOrganizationAdmin(requestingUser.role) && oid != -1) {
            Organization organization = Organization.findById(oid)
            if(organization != null) {
                if(requestingUser.role.type == RoleType.ADMIN) {
                    res.data = organization.users
                } else {
                }
            }
        }
    }
    */


    QueryResult<Organization> createOrganization(AuthToken token, String description, String name, QueryResult<Organization> result = new QueryResult<>(success: true)) {
        User orgAdmin = token?.user
        if(isOrganizationAdmin(orgAdmin.role)) {
            Organization organization = new Organization(name: name, description: description)
            organization.save(flush: true, failOnError: true)
            result.data = organization
        } else {
            QueryResult.fromHttpStatus(HttpStatus.BAD_REQUEST, result)
        }
        result
    }
}