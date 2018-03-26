package organizer

import util.RoleType

class Role {

    RoleType type
    RoleType master

    static belongsTo = [user: User]
    //static hasOne = [organization:Organization]

    static mapping = {}

    static constraints = {
       // organization nullable: true
    }
}
