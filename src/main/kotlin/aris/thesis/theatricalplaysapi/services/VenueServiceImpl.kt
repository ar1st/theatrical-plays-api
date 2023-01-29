package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.curators.DataCurator
import aris.thesis.theatricalplaysapi.dtos.request.CreateVenueRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.entities.Venue
import aris.thesis.theatricalplaysapi.exceptions.error.illegalDelete
import aris.thesis.theatricalplaysapi.exceptions.error.notFound
import aris.thesis.theatricalplaysapi.repositories.EventRepository
import aris.thesis.theatricalplaysapi.repositories.VenueRepository
import aris.thesis.theatricalplaysapi.services.types.VenueService
import aris.thesis.theatricalplaysapi.utils.doIfTrue
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VenueServiceImpl(val venueRepository: VenueRepository, val eventRepository: EventRepository) : VenueService {

    override fun getAllVenues(page: Int, size: Int): Page<Venue> {
        return if (page >= 0 && size > 0) {
            venueRepository.findAll(PageRequest.of(page, size))
        } else {
            venueRepository.findAll(Pageable.unpaged())
        }
    }

    override fun getVenueById(venueId: Int): Venue? {
        return venueRepository.findByIdOrNull(venueId)
    }

    override fun getVenueByEventId(eventId: Int): Venue {
        return venueRepository.findVenueByEventId(eventId)
    }

    @Transactional
    override fun createVenue(request: CreateVenueRequest): EntityId {
        val venueByTitle = venueRepository.findByTitle(request.title)
        val id = EntityId()

        if (venueByTitle == null) {
            DataCurator.curateVenueTitle(request.title)
            val venue = venueRepository.save(Venue(request.title, request.address))
            id.newId = venue.id
        } else {
            id.existingId = venueByTitle.id
            venueByTitle.address = request.address
        }

        return id
    }

    @Transactional
    override fun deleteVenue(venueId: Int) {
        venueRepository.findByIdOrNull(venueId) ?: notFound("Venue", venueId.toString())
        eventRepository.existsByVenueID(venueId).doIfTrue { illegalDelete() }

        venueRepository.deleteById(venueId)
    }

}