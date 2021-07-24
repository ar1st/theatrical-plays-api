package aris.thesis.theatricalplaysapi.dtos

import aris.thesis.theatricalplaysapi.entities.Image
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.entities.Role

class PersonRoleDTO(person: Person, role: Role, image: Image?): DataTransferObject {
    val id = person.id
    val fullName = person.fullName
    val role = role.role
    val image = image?.imageURL
}