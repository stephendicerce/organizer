
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

/**
 *
 * @param token
 * @param description
 * @param name
 * @param result
 * @return
 */
    QueryResult<Organization> createOrganization(AuthToken token, String description, String name, QueryResult<Organization> result = new QueryResult<>(success: true)) {
        User orgOwner = token?.user
        Organization organization = new Organization(name: name, description: description, orgOwner: orgOwner, calendar: new Calendar())
        organization.save(flush: true, failOnError: true)
        result.data = organization

        result
    }

    /**
     * Method to delete the organization.
     * @param token The requesting User's organization.
     * @param organizationId
     * @return
     */
    QueryResult<Organization> deleteOrganization(AuthToken token, String organizationId) {
        QueryResult<Organization> res = new QueryResult<>()
        User requestingUser = token?.user
        long oid = organizationId.isLong() ? organizationId.toLong() : -1

        if(requestingUser != null && oid != -1) {
            Organization organization = Organization.findById(oid)
            if(organization != null) {
                if(isOwnerOf(requestingUser, organization)) {
                    doDelete(organization, res)
                } else {
                    QueryResult.fromHttpStatus(HttpStatus.UNAUTHORIZED, res)
                }
            } else {
                QueryResult.fromHttpStatus(HttpStatus.BAD_REQUEST, res)
            }
        } else {
            QueryResult.fromHttpStatus(HttpStatus.UNAUTHORIZED, res)
        }
    }

    /**
     *
     * @param organization
     * @param result
     * @return
     */
    private QueryResult<Organization> doDelete(Organization organization, QueryResult<Organization> result = new QueryResult<>(success: true)) {
        def events = Event.findByOrganization(organization)
        for(event in events) {
            event.delete(flush: true, failOnError: true)
        }
        organization.delete(flush: true, failOnError: true)
    }



    private boolean isOwnerOf(User user, Organization organization) {
        long userId = user.id
        long organizationOwnerId = organization.orgOwner.id

        if(userId == organizationOwnerId)
            true
        else
            false
    }
    /**
     *
     * @param token
     * @return
     */
    QueryResult<List<Organization>> getAllOrganizations(AuthToken token) {

    }

    /**
     *
     * @param token
     * @param organization_id
     * @return
     */
    QueryResult<List<Organization>> getAllOrganizations(AuthToken token, String organization_id) {

    }


}