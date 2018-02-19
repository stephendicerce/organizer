package organizer

import util.QueryResult

class OrganizationController {

    static responseFormats = ['json', 'xml']

    PreconditionService preconditionService
    OrganizationService organizationService


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
}
