package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.entities.Event
import aris.thesis.theatricalplaysapi.services.proto.ModelService

interface EventService: ModelService {

    fun getEventsByProductionId( productionId: Int): List<Event>

}