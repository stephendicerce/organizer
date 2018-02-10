package organizer

class Organization {

    String name
    String description


    static hasMany = [users: User, events: Event]
    static belongsTo = [admin: User]

    static constraints = {
    }
}
