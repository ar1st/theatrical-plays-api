package aris.thesis.theatricalplaysapi.dtos

import aris.thesis.theatricalplaysapi.entities.Event
import aris.thesis.theatricalplaysapi.entities.Venue

class EventVenueDTO(event: Event, venue: Venue): DataTransferObject {
    val eventId = event.id
    val date = event.dateEvent
    val priceRange = event.priceRange
    val venueId = venue.id
    val title = venue.title
    val address = venue.address
}