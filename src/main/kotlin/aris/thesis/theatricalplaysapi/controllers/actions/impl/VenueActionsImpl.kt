package aris.thesis.theatricalplaysapi.controllers.actions.impl

import aris.thesis.theatricalplaysapi.controllers.actions.def.VenueActions
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import aris.thesis.theatricalplaysapi.dtos.VenueDTO
import aris.thesis.theatricalplaysapi.exceptions.error.notFound
import aris.thesis.theatricalplaysapi.services.proto.ModelServiceConsumer2
import aris.thesis.theatricalplaysapi.services.types.ProductionService
import aris.thesis.theatricalplaysapi.services.types.VenueService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
@Suppress("unused")
class VenueActionsImpl: VenueActions, ModelServiceConsumer2<VenueService, ProductionService>() {

    override fun getAllVenues(page: Int, size: Int): ApiResponse<Page<VenueDTO>, String> {
        val venues = firstService.getAllVenues(page, size)

        return ApiResponse(venues.map { VenueDTO(it) }, null, HttpStatus.OK.name)
    }

    override fun getVenueById(venueId: Int): ApiResponse<VenueDTO, String> {
        val venue = firstService.getVenueById(venueId)?: notFound("Venue", venueId.toString())

        return ApiResponse(VenueDTO(venue), null, HttpStatus.OK.name)
    }

    override fun getProductionsByVenueId(venueId: Int, page: Int, size: Int): ApiResponse<Page<ProductionDTO>, String> {
        firstService.getVenueById(venueId)?: notFound("Venue", venueId.toString())

        val productionsByVenueId = secondService.getProductionsByVenueId(venueId, page, size)

        return ApiResponse( productionsByVenueId.map { ProductionDTO(it) }, null, HttpStatus.OK.name)
    }
}