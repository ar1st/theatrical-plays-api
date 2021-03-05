package aris.thesis.theatricalplaysapi.dtos

import aris.thesis.theatricalplaysapi.entities.Person

class PersonDTO(person: Person): DataTransferObject<Person> {
    val id = person.id
    val fullName = person.fullName
}