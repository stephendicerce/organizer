package organizer

import grails.gorm.transactions.Transactional
import util.QueryResult

@Transactional
class EventService {

    /**
     * Method to create an event
     * @param token the access token of the requesting user
     * @param orgId (Optional) the organization the event will belong to
     * @param name The name of the event
     * @param description (Optional) a short description of the event
     * @param startingMonth (Optional) A string representing the number of the month when the event will begin
     * @param startingDay (Optional) A string representing the number of the day when the event will begin
     * @param startingYear (Optional) A string representing the year when the event will begin
     * @param dueMonth A string representing the number of the month when the event will end
     * @param dueDay A string representing the number of the day when the event will end
     * @param dueYear A string representing the number of the year when the event will end
     * @param dueMinute (Optional) A string representing the minute when the event is due
     * @param dueHour (Optional) A string representing the hour when the event is due
     * @param color (Optional) A string representing the color the event will be displayed in on the User's Calendar
     * @param res A QueryResult stores data from the method
     * @return A QueryResult The result of the method
     */
    QueryResult<Event> createEvent(AuthToken token, String orgId, String name, String description, String startingMonth, String startingDay, String startingYear, String dueMonth, String dueDay, String dueYear, String dueMinute, String dueHour, String color, QueryResult<Event> res = new QueryResult<>(success: true)) {
        int monthDue = dueMonth.isInteger() ? dueMonth.toInteger() : -1
        int dayDue = dueDay.isInteger() ? dueDay.toInteger() : -1
        int yearDue = dueYear.isInteger() ? dueYear.toInteger() : -1
        Organization organization

        User user = token?.user
        Event event = new Event(name: name, dueMonth: monthDue, dueDay: dayDue, dueYear: yearDue)


        if(description != null)
            event.description = description
        if(startingMonth!=null)
            event.startingMonth = startingMonth.isInteger() ? startingMonth.toInteger() : -1
        if(startingYear != null)
            event.startingYear = startingYear.isInteger() ? startingYear.toInteger() : -1
        if(startingDay != null)
            event.startingDay = startingDay.isInteger() ? startingDay.toInteger() : -1
        if(dueHour != null)
            event.dueHour = dueHour.isInteger() ? dueHour.toInteger() : -1
        if(dueMinute != null)
            event.dueMinute = dueMinute.isInteger() ? dueMinute.toInteger() : -1
        if(color != null)
            event.color = color

        if(orgId != null) {
            Long organizationId = orgId.isLong() ? orgId.toLong() : -1
            organization = Organization.findById(organizationId)
            if(canUserCreateOrgEvent(organization, user))
                res = createOrganizationEvent(organization, event, res)
        } else {
            res = createUserEvent(user, event, res)
        }
        res
    }

    /**
     * A Method that creates an event that is owned by an organization
     * @param organization The organization that owns the event
     * @param event The event in the process of being created
     * @param result A QueryResult to store the data
     * @return the result of the event being created
     */
    QueryResult<Event> createOrganizationEvent(Organization organization, Event event, QueryResult<Event> result) {
        event.organization = organization
        event.save(flush: true, failOnError: true)
        result.data = event
        result
    }

    /**
     * A method to create an event that is owned by a user
     * @param user The user that will own the event
     * @param event The event in the process of being created
     * @param result A QueryResult to store data
     * @return The result of the event being created
     */
    QueryResult<Event> createUserEvent(User user, Event event, QueryResult<Event> result) {
        event.user = user
        event.save(flush: true, failOnError: true)
        result.data = event
        result
    }

    /**
     * A method to get the event
     * @param token The access token of the requesting user
     * @param eventId The id of the event that the user is requesting
     * @param result A QueryResult that can store data
     * @return The event
     */
    QueryResult<Event> getEvent(AuthToken token, String eventId, QueryResult<Event> result = new QueryResult<>(success: true)) {
        User requestingUser = token?.user
        Long eID = eventId.isLong() ? eventId.toLong() : -1

        if(requestingUser != null && eID != -1) {
            Event event = Event.findById(eID)
            if(event.user != null) {
                if(event.user.id == requestingUser.id)
                    result.data = event
            } else if(event.organization != null) {
                if(isInOrganization(event.organization, requestingUser))
                    result.data = event
            }
        }
        result
    }

    QueryResult<Event> editEvent(String eventID, AuthToken token, String orgId, String name, String description, String startingMonth, String startingDay, String startingYear, String dueMonth, String dueDay, String dueYear, String dueMinute, String dueHour, String color, QueryResult<Event> res = new QueryResult<>(success: true)) {
        User requestingUser = token?.user
        Long eID = eventID.isLong() ? eventID.toLong() : -1
        Event event = Event.findById(eID)

        int startMonth = startingMonth.isInteger() ? startingMonth.toInteger() : event.startingMonth
        int startDay = startingDay.isInteger() ? startingMonth.toInteger() : event.startingDay
        int startYear = startingYear.isInteger() ? startingYear.toInteger() : event.startingYear
        int monthDue = dueMonth.isInteger() ? dueMonth.toInteger() : event.dueMonth
        int dayDue = dueDay.isInteger() ? dueDay.toInteger() : event.dueDay
        int yearDue = dueYear.isInteger() ? dueYear.toInteger() : event.dueYear
        int minuteDue = dueMinute.toInteger() ? dueMinute.toInteger() : event.dueMinute
        int hourDue = dueHour.isInteger() ? dueHour.toInteger() : event.dueHour

        if(event != null) {
            if (requestingUser != null) {
                if((event.user.id == requestingUser.id) ||((event.organization.id != null) && (canUserCreateOrgEvent(event.organization, requestingUser)))) {
                    if (!event.name.equals(name))
                        event.name = name
                    if (!event.description.equals(description))
                        event.description = description
                    if (event.startingMonth != startMonth)
                        event.startingMonth = startMonth
                    if (event.startingDay != startingDay)
                        event.startingDay = startDay
                    if (event.startingYear != startYear)
                        event.startingYear = startYear
                    if (event.dueMonth != monthDue)
                        event.dueMonth = monthDue
                    if (event.dueDay != dayDue)
                        event.dueDay = dayDue
                    if (event.dueYear != yearDue)
                        event.dueYear = yearDue
                    if (event.dueMinute != minuteDue)
                        event.dueMinute = minuteDue
                    if (event.dueHour != hourDue)
                        event.dueHour = hourDue
                    if (!event.color.equals(color))
                        event.color = color
                }
            }
        } else {
            res = createEvent(token, orgId, name, description, startingMonth, startingDay, startingYear, dueMonth, dueDay, dueYear, dueMinute, dueHour, color, res)
        }

        res.data = event
        res
    }

    /**
     * A method to check if the User is part of an organization
     * @param organization The organization in question
     * @param requestingUser The requesting user
     * @return true if the user is a part of the organization, false if the user is not part of the organization
     */
    boolean isInOrganization(Organization organization, User requestingUser) {
        for(User user: organization.users){
            if(user.id == requestingUser.id)
                return true
        }
        for(User admin: organization.admins) {
            if(admin.id == requestingUser.id)
                return true
        }
        if(organization.orgOwner.id == requestingUser.id)
            return true
        return false
    }

    /**
     * A method to check if the user has read/write access in the organization
     * @param organization The organization in question
     * @param user The requesting user
     * @return true if the user has read/write access, false if the user does not have read write access
     */
    boolean canUserCreateOrgEvent(Organization organization, User user) {
        for (User admin : organization.admins) {
            if(admin.id == user.id)
                true
        }
        if(organization.orgOwner.id == user.id)
            true
        false
    }
}
