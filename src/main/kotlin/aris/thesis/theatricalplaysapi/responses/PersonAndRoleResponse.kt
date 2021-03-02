package aris.thesis.theatricalplaysapi.responses

import com.fasterxml.jackson.annotation.JsonAlias

data class PersonAndRoleResponse (
    @JsonAlias("Role")
    var role: String?,
    @JsonAlias("Fullname")
    var fullName: String?,
    @JsonAlias("ID")
    var id: Int?

    )