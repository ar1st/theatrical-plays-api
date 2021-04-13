package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.controllers.actions.def.VenueActions
import aris.thesis.theatricalplaysapi.controllers.base.TheatricalPlaysRestController
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import aris.thesis.theatricalplaysapi.dtos.VenueDTO
import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/venues", produces = [MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8"])
class VenueController: TheatricalPlaysRestController<VenueActions>() {

//    page minValue = 0, size minValue=1
//    values less than the aforementioned will return the all the elements
    @GetMapping("")
    fun getAllVenues(@RequestParam(required = false) page: Int?,
                     @RequestParam(required = false) size: Int?) : ApiResponse<Page<VenueDTO>, String> {
        return executor.getAllVenues(page?: -1 ,size?: -1)
    }

    @GetMapping("/{ID}")
    fun getById(@PathVariable("ID") venueId: Int) : ApiResponse<VenueDTO, String> {
        return executor.getVenueById(venueId)
    }

    //not yet functional
    @GetMapping("/{ID}/productions")
    fun getProductionsByVenueId(@PathVariable("ID") venueId: Int,
                                @RequestParam(required = false) page: Int?,
                                @RequestParam(required = false) size: Int?): ApiResponse<Page<ProductionDTO>, String> {
        return executor.getProductionsByVenueId(venueId, page?: -1, size?: -1)
    }

}