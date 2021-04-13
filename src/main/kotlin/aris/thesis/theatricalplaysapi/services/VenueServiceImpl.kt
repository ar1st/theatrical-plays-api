package aris.thesis.theatricalplaysapi.services


import aris.thesis.theatricalplaysapi.entities.Venue
import aris.thesis.theatricalplaysapi.repositories.VenueRepository
import aris.thesis.theatricalplaysapi.services.types.VenueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class VenueServiceImpl: VenueService {
    //repos
    @Autowired
    lateinit var venueRepository: VenueRepository

    override fun getAllVenues(pageable: Pageable): Page<Venue> {
        return venueRepository.findAll(pageable)
    }

    override fun getVenueById(venueId: Int): Venue? {
        return venueRepository.findByIdOrNull(venueId)
    }

    override fun getVenueByEventId(eventId: Int): Venue {
        return venueRepository.findVenueByEventId(eventId)
    }

}