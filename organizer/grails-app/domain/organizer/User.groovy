package organizer

class User {

    String firstName
    String lastName
    String email
    String imageUrl

    static hasOne = [authToken: AuthToken, role: Role, calender: Calender]

    static hasMany = [organizations: Organization, friends: User]

    static mapping = {
        table "users"
        version false
    }
    static constraints = {
        firstName nullable: true
        lastName nullable: true
        email unique: true
        authToken nullable: true
        imageUrl nullable: true, blank: false
    }
}
