package aris.thesis.theatricalplaysapi.utils

import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import aris.thesis.theatricalplaysapi.dtos.VenueDTO
import aris.thesis.theatricalplaysapi.entities.Image
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.entities.Venue
import org.springframework.data.domain.Page

//People
fun Person.asPersonDTO(image: Image?): PersonDTO {
    return PersonDTO(this, image)
}


//Productions
fun Production.asProductionDTO(): ProductionDTO {
    return  ProductionDTO(this)
}

fun Page<Production>.asPageProductionDTO(): Page<ProductionDTO> {
    return this.map{ it.asProductionDTO() }
}


//Venues
fun Venue.asVenueDTO(): VenueDTO {
    return VenueDTO(this)
}

fun Page<Venue>.asPageVenueDTO(): Page<VenueDTO> {
    return this.map{ it.asVenueDTO() }
}

