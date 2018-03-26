package organizer

class Event {

    String name
    String description
    Date startingDate
    Date dueDate
    String dueHour
    String dueMinute
    String color

    static belongsTo = [user: User, organization: Organization]


    static constraints = {
        startingDate nullable: true
        description nullable: true
        dueHour nullable: true
        dueMinute nullable: true
        dueDate nullable: false
        color nullable: true
    }

}
