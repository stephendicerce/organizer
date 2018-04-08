package organizer

import util.QueryResult

class OrganizationController {

    static responseFormats = ['json', 'xml']

    PreconditionService preconditionService
    OrganizationService organizationService

    /**
     *
     * @param access_token
     * @param organization_id
     * @return
     */
    def organizationGet(String access_token, String organization_id) {
        def require = preconditionService.notNull(params, ["access_token"])
        def token = preconditionService.accessToken(access_token,require).data

        if(require.success) {
            QueryResult<List<Organization>> result = organization_id == null ?
                    organizationService.getAllOrganizations(token)
                    : organizationService.getAllOrganizations(token, organization_id)
            if(result.success) {
                render(view: 'organizationList', model: [token: token, organizations: result.data])
            } else {
                render(view: '../failure', model: [errorCode: result.errorCode, message: result.message])
            }
        } else {
            render(view: '../failure', model: [errorCode: require.errorCode, message: require.message])
        }
    }

    /**
     *
     * @param access_token
     * @param description
     * @param name
     * @return
     */
    def postOrganization(String access_token, String description, String name) {
        def require = preconditionService.notNull(params, ["access_token", "description", "name"])
        def token = preconditionService.accessToken(access_token, require).data

        if(require.success) {
            def result = organizationService.createOrganization(token, description, name)
            if(result.success) {
                render(view: 'newOrganization', model: [organization: result.data])
            } else {
                render(view: '../failure', model: [errorCode: result.errorCode, message: result.message])
            }
        } else {
            render(view: '../failure', model: [errorCode: require.errorCode, message: require.message])
        }
    }

    /**
     *
     * @param access_token
     * @param organization_id
     * @return
     */
    def deleteOrganization(String access_token, String organization_id) {
        def require = preconditionService.notNull(params, ["access_token", "organization_id"])
        def token = preconditionService.accessToken(access_token,require).data

        if(require.success) {
            def result = organizationService.deleteOrganization(token, organization_id)
            if(result.success) {
                render(view: 'deleteResult', model: [token: token])
            } else {
                render(view: '../failure', model: [errorCode: result.errorCode, message: result.message])
            }
        } else {
            render(view: '../failure', model: [errorCode: require.errorCode, message: require.message])
        }
    }
}
