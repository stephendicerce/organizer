package organizer

import util.QueryResult

class EventController {

    static responseFormats = ['json', 'xml']

    PreconditionService preconditionService
    EventService eventService

    def getUserEvent(String accessToken, String eventId) {
        def require = preconditionService.notNull(params, ["accessToken"])
        def token = preconditionService.accessToken(accessToken, require).data

        if(require.success) {
            def result = eventService.getUserEvent(token, eventId)

            if(result.success) {
                render(view: 'userEvent', model: [token: token, event: result.data])
            } else {
                render(view: '../failure', model: [errorCode: result.errorCode, message: result.message])
            }

        } else {
            render(view: '../failure', model: [errorCode: require.errorCode, message: require.message])
        }
    }

    def putEvent(String accessToken, String orgId, String name, String description, String startingMonth, String startingDay, String startingYear, String dueMonth, String dueDay, String dueYear, String dueMinute, String dueHour, String color, String privacyString) {
        def require = preconditionService.notNull(params, ["accessToken", "name", "dueMonth", "dueDay", "dueYear", "privacyString"])
        def token = preconditionService.accessToken(accessToken, require).data

        if(require.success) {
            def result = eventService.createEvent(token, orgId, name, description, startingMonth, startingDay, startingYear, dueMonth, dueDay, dueYear, dueMinute, dueHour, color, privacyString)

            if(result.success) {
                render(view: 'event', model: [token: token, event: result.data])
            } else {
                render(view: '../failure', model: [errorCode: result.errorCode, message: result.message])
            }
        } else {
            render(view: '../failure', model: [errorCode: require.errorCode, message: require.message])
        }
    }

    def postUserEvent(String eventId, String accessToken, String orgId, String name, String description, String startingMonth, String startingDay, String startingYear, String dueMonth, String dueDay, String dueYear, String dueMinute, String dueHour, String color, String privacyString) {
        def require = preconditionService.notNull(params, ["accessToken"])
        def token = preconditionService.accessToken(accessToken, require).data

        if(require.success) {
            def result = eventService.editEvent(eventId, token, orgId, name, description, startingMonth, startingDay, startingYear, dueMonth, dueDay, dueYear, dueMinute, dueHour, color, privacyString)

            if(result.success) {
                render(view: 'userEvent', model: [token: token, event: result.data])
            } else {
                render(view: '../failure', model: [errorCode: result.errorCode, message: result.message])
            }
        } else {
            render(view: '../failure', model: [errorCode: require.errorCode, message: require.message])
        }
    }

    def deleteEvent(String accessToken, String eventId) {
        def require = preconditionService.notNull(params, ["accessToken", "eventId"])
        def token = preconditionService.accessToken(accessToken, require).data

        if(require.success) {
            def result = eventService.deleteEvent(token, eventId)

            if(result.success) {
                render(view: "deleteEvent", model: [token: token])
            } else {
                render(view: '../failure', model: [errorCode: result.errorCode, message: result.message])
            }
        } else {
            render(view: '../failure', model: [errorCode: require.errorCode, message: require.message])
        }
    }
}
