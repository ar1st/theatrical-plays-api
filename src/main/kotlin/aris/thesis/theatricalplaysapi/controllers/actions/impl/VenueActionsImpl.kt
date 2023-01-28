package aris.thesis.theatricalplaysapi.controllers.actions.impl

import aris.thesis.theatricalplaysapi.controllers.actions.ActionExecutor
import aris.thesis.theatricalplaysapi.controllers.actions.Actions
import aris.thesis.theatricalplaysapi.rest.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import aris.thesis.theatricalplaysapi.dtos.VenueDTO
import aris.thesis.theatricalplaysapi.exceptions.error.notFound
import aris.thesis.theatricalplaysapi.services.proto.ModelServiceConsumer2
import aris.thesis.theatricalplaysapi.services.types.ProductionService
import aris.thesis.theatricalplaysapi.services.types.VenueService
import aris.thesis.theatricalplaysapi.utils.asPageProductionDTO
import aris.thesis.theatricalplaysapi.utils.asPageVenueDTO
import aris.thesis.theatricalplaysapi.utils.asVenueDTO
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
@Suppress("unused")
class VenueActionsImpl : ActionExecutor<Actions.Venue>, ModelServiceConsumer2<VenueService, ProductionService>() {

    fun getAllVenues(page: Int, size: Int): ApiResponse<Page<VenueDTO>> {
        val venues = firstService.getAllVenues(page, size)

        return ApiResponse(venues.asPageVenueDTO(), null, HttpStatus.OK.name)
    }

    fun getVenueById(venueId: Int): ApiResponse<VenueDTO> {
        val venue = firstService.getVenueById(venueId) ?: notFound("Venue", venueId.toString())

        return ApiResponse(venue.asVenueDTO(), null, HttpStatus.OK.name)
    }

    fun getProductionsByVenueId(venueId: Int, page: Int, size: Int): ApiResponse<Page<ProductionDTO>> {
        firstService.getVenueById(venueId) ?: notFound("Venue", venueId.toString())

        val productionsByVenueId = secondService.getProductionsByVenueId(venueId, page, size)

        return ApiResponse(productionsByVenueId.asPageProductionDTO(), null, HttpStatus.OK.name)
    }
}