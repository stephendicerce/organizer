package organizer

class Calender {



    static hasMany = [events: Event]
    static belongsTo = [user: User]
    static constraints = {


    }
}
