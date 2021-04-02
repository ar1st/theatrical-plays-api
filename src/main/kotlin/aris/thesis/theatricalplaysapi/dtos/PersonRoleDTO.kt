package aris.thesis.theatricalplaysapi.dtos

import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.entities.Role

class PersonRoleDTO(person: Person, role: Role): DataTransferObject<Person> {
    val id = person.id
    val fullName = person.fullName
    val role = role.role
}