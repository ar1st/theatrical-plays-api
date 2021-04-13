package aris.thesis.theatricalplaysapi.dtos

import aris.thesis.theatricalplaysapi.entities.Venue

class VenueDTO(venue: Venue): DataTransferObject<Venue> {
    val id = venue.id
    val title = venue.title
    val address = venue.address
}