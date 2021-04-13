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
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
@Suppress("unused")
class VenueActionsImpl: VenueActions, ModelServiceConsumer2<VenueService, ProductionService>() {

    override fun getAllVenues(page: Int, size: Int): ApiResponse<Page<VenueDTO>, String> {

        val paginatedResult = if (page >= 0 && size > 0)
            firstService.getAllVenues(PageRequest.of(page, size))
        else
            firstService.getAllVenues( Pageable.unpaged())

        return ApiResponse(paginatedResult.map { VenueDTO(it) }, null, HttpStatus.OK.name)
    }

    override fun getVenueById(venueId: Int): ApiResponse<VenueDTO, String> {
        val venue = firstService.getVenueById(venueId)?: notFound("Venue", venueId.toString())

        return ApiResponse(VenueDTO(venue), null, HttpStatus.OK.name)
    }

    override fun getProductionsByVenueId(venueId: Int, page: Int, size: Int): ApiResponse<Page<ProductionDTO>, String> {
        val venue = firstService.getVenueById(venueId)?: notFound("Venue", venueId.toString())

        TODO()
    }
}