package organizer

import util.RoleType

class Role {

    RoleType type
    RoleType master

    static belongsTo = [user: User]

    static mapping = {}

    static constraints = {

    }
}
