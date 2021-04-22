package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.entities.Venue
import aris.thesis.theatricalplaysapi.services.proto.ModelService
import org.springframework.data.domain.Page

interface VenueService: ModelService {
    fun getAllVenues(page: Int, size: Int): Page<Venue>
    fun getVenueById(venueId: Int): Venue?

    fun getVenueByEventId(eventId: Int): Venue
}