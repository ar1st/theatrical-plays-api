package aris.thesis.theatricalplaysapi.dtos

import aris.thesis.theatricalplaysapi.entities.Image
import aris.thesis.theatricalplaysapi.entities.Person

class PersonDTO(person: Person, image: Image? = Image()): DataTransferObject<Person> {
    val id = person.id
    val fullName = person.fullName
    val image: String? = image?.imgURL
}