package aris.thesis.theatricalplaysapi.dtos

import aris.thesis.theatricalplaysapi.entities.Image
import aris.thesis.theatricalplaysapi.entities.Person

class PersonDTO(person: Person, image: Set<Image> = emptySet()) : DataTransferObject {
    val id = person.id
    val fullName = person.fullName
    val image: String = image.map { it.imageURL ?: "" }.toList().firstOrNull() ?: ""
}