package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.entities.Event
import aris.thesis.theatricalplaysapi.repositories.EventRepository
import aris.thesis.theatricalplaysapi.services.types.EventService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EventServiceImpl: EventService {
    //repos
    @Autowired
    lateinit var eventRepository: EventRepository

    override fun getEventsByProductionId(productionId: Int): List<Event> {
        return eventRepository.findEventsByProductionID(productionId)
    }
}