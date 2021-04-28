package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.controllers.actions.def.ProductionActions
import aris.thesis.theatricalplaysapi.controllers.base.TheatricalPlaysRestController
import aris.thesis.theatricalplaysapi.rest.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.EventVenueDTO
import aris.thesis.theatricalplaysapi.dtos.PersonRoleDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/productions", produces = [MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8"])
class ProductionController: TheatricalPlaysRestController<ProductionActions>() {

    @GetMapping("")
    fun getAll(@RequestParam(required = false) page: Int?,
               @RequestParam(required = false) size: Int?): ApiResponse<Page<ProductionDTO>, String> {
        return executor.getAllProductions(page?: -1, size?: -1)
    }

    @GetMapping("/{productionId}")
    fun getById(@PathVariable("productionId") productionId: Int,
                response: HttpServletResponse): ApiResponse<ProductionDTO, String> {
        return executor.getProduction(productionId, response)
    }

    @GetMapping("/latest")
    fun getLatestProductions(@RequestParam(required = false) page: Int?,
                             @RequestParam(required = false) size: Int?,): ApiResponse<Page<ProductionDTO>, String> {
        return executor.getLatestProductions(page?: -1, size?: -1)
    }

    @GetMapping("/{productionId}/people")
    fun getPeopleByProduction(@PathVariable("productionId") productionId: Int):
            ApiResponse<List<PersonRoleDTO>, String> {
        return executor.getPeopleByProductionId(productionId)
    }

    @GetMapping("/{productionId}/events")
    fun getEventsAndVenuesByProduction(@PathVariable("productionId") productionId: Int):
            ApiResponse<List<EventVenueDTO>, String> {
        return executor.getEventsAndVenuesByProduction(productionId)
    }

    @GetMapping("/search")
    fun searchProductions(@RequestParam("q") query: String,
                     @RequestParam(required = false) page: Int?,
                     @RequestParam(required = false) size: Int?,
                     response: HttpServletResponse): ApiResponse<Page<ProductionDTO>, String> {
        return executor.searchProduction(query, page?: -1, size ?: -1, response)
    }
}