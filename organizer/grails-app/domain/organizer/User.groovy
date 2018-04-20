package organizer

class User {

    String firstName
    String lastName
    String email
    String imageUrl
    Calendar calendar

    static hasOne = [authToken: AuthToken]

    static hasMany = [friends: User, requestedFriends: User]

    static mapping = {
       /* table "users"
        version false*/
    }
    static constraints = {
        firstName nullable: true
        lastName nullable: true
        email unique: true
        authToken nullable: true
        imageUrl nullable: true, blank: false
    }
}
