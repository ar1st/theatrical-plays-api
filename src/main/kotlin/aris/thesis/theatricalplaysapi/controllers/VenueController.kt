package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.constants.RestPathConstants
import aris.thesis.theatricalplaysapi.controllers.actions.impl.VenueActionsImpl
import aris.thesis.theatricalplaysapi.controllers.base.TheatricalPlaysRestController
import aris.thesis.theatricalplaysapi.rest.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import aris.thesis.theatricalplaysapi.dtos.VenueDTO
import aris.thesis.theatricalplaysapi.dtos.request.CreateVenueRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.services.types.VenueService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(RestPathConstants.REST_BASE_PATH_VENUES,
                produces = [MediaType.APPLICATION_JSON_VALUE + RestPathConstants.MEDIA_TYPE_UTF_8])
class VenueController(val venueService: VenueService) : TheatricalPlaysRestController<VenueActionsImpl>() {

    //page minValue = 0, size minValue=1
    //values less than the aforementioned will return the all the elements
    @GetMapping("")
    fun getAllVenues(@RequestParam(required = false) page: Int?,
                     @RequestParam(required = false) size: Int?): ApiResponse<Page<VenueDTO>> {
        return executor.getAllVenues(page ?: -1, size ?: -1)
    }

    @GetMapping(RestPathConstants.REST_PATH_VENUE_ID)
    fun getById(@PathVariable("venueId") venueId: Int): ApiResponse<VenueDTO> {
        return executor.getVenueById(venueId)
    }

    //not yet functional
    @GetMapping(RestPathConstants.REST_PATH_VENUE_ID + RestPathConstants.REST_PATH_PRODUCTIONS)
    fun getProductionsByVenueId(@PathVariable("venueId") venueId: Int,
                                @RequestParam(required = false) page: Int?,
                                @RequestParam(required = false) size: Int?): ApiResponse<Page<ProductionDTO>> {
        return executor.getProductionsByVenueId(venueId, page ?: -1, size ?: -1)
    }

    @PostMapping
    fun createVenue(@RequestBody request: CreateVenueRequest): ApiResponse<EntityId> {
        val id = venueService.createVenue(request)

        return ApiResponse(id, null, HttpStatus.OK.name)
    }

    @DeleteMapping("/{venueId}")
    fun deleteVenue(@PathVariable venueId: Int): ApiResponse<String?> {
        venueService.deleteVenue(venueId)

        return ApiResponse(status = HttpStatus.OK.name)
    }

}