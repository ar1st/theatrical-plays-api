package aris.thesis.theatricalplaysapi.controllers.actions.def

import aris.thesis.theatricalplaysapi.controllers.actions.ActionExecutor
import aris.thesis.theatricalplaysapi.controllers.actions.Actions
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import aris.thesis.theatricalplaysapi.dtos.VenueDTO
import org.springframework.data.domain.Page

interface VenueActions: ActionExecutor<Actions.Venue> {

    fun getAllVenues(page: Int, size : Int): ApiResponse<Page<VenueDTO>, String>
    fun getVenueById(venueId: Int): ApiResponse<VenueDTO, String>

    fun getProductionsByVenueId(venueId: Int, page: Int, size : Int): ApiResponse<Page<ProductionDTO>,String>
}