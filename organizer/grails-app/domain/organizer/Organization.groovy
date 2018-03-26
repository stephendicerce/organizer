package organizer

class Organization {

    String name
    String description
    Calendar calendar


    static hasMany = [users: User, admins:User, events: Event]
    static belongsTo = [orgOwner: User]

    static constraints = {
        description nullable: true
        calendar nullable: false
    }
}
