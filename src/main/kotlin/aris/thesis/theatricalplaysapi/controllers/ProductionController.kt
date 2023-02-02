package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.constants.*
import aris.thesis.theatricalplaysapi.constants.RestPathConstants.REST_PATH_EVENTS
import aris.thesis.theatricalplaysapi.constants.RestPathConstants.REST_PATH_PRODUCTION_ID
import aris.thesis.theatricalplaysapi.controllers.actions.impl.ProductionActionsImpl
import aris.thesis.theatricalplaysapi.controllers.base.TheatricalPlaysRestController
import aris.thesis.theatricalplaysapi.dtos.EventVenueDTO
import aris.thesis.theatricalplaysapi.dtos.PersonRoleDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import aris.thesis.theatricalplaysapi.dtos.request.CreateEventRequest
import aris.thesis.theatricalplaysapi.dtos.request.CreateProductionRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.rest.ApiResponse
import aris.thesis.theatricalplaysapi.security.permission.IsAdminOrUser
import aris.thesis.theatricalplaysapi.services.types.ProductionService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(RestPathConstants.REST_BASE_PATH_PRODUCTIONS,
                produces = [MediaType.APPLICATION_JSON_VALUE + RestPathConstants.MEDIA_TYPE_UTF_8])
class ProductionController(val productionService: ProductionService) : TheatricalPlaysRestController<ProductionActionsImpl>() {

    @GetMapping("")
    @IsAdminOrUser
    fun getAll(@RequestParam(required = false) page: Int?,
               @RequestParam(required = false) size: Int?): ApiResponse<Page<ProductionDTO>> {
        return executor.getAllProductions(page ?: -1, size ?: -1)
    }

    @GetMapping(RestPathConstants.REST_PATH_PRODUCTION_ID)
    fun getById(@PathVariable("productionId") productionId: Int,
                response: HttpServletResponse): ApiResponse<ProductionDTO> {
        return executor.getProduction(productionId, response)
    }

    @GetMapping(RestPathConstants.REST_PATH_LATEST)
    fun getLatestProductions(
        @RequestParam(required = false) page: Int?,
        @RequestParam(required = false) size: Int?,
    ): ApiResponse<Page<ProductionDTO>> {
        return executor.getLatestProductions(page ?: -1, size ?: -1)
    }

    @GetMapping(RestPathConstants.REST_PATH_PRODUCTION_ID + RestPathConstants.REST_PATH_PEOPLE)
    fun getPeopleByProduction(@PathVariable("productionId") productionId: Int):
            ApiResponse<List<PersonRoleDTO>> {
        return executor.getPeopleByProductionId(productionId)
    }

    @GetMapping(RestPathConstants.REST_PATH_PRODUCTION_ID + RestPathConstants.REST_PATH_EVENTS)
    fun getEventsAndVenuesByProduction(@PathVariable("productionId") productionId: Int):
            ApiResponse<List<EventVenueDTO>> {
        return executor.getEventsAndVenuesByProduction(productionId)
    }

    @GetMapping(RestPathConstants.REST_PATH_SEARCH)
    fun searchProductions(@RequestParam("q") query: String,
                          @RequestParam(required = false) page: Int?,
                          @RequestParam(required = false) size: Int?,
                          response: HttpServletResponse): ApiResponse<Page<ProductionDTO>> {
        return executor.searchProduction(query, page ?: -1, size ?: -1, response)
    }

    @PostMapping
    fun createVenue(@RequestBody request: CreateProductionRequest): ApiResponse<EntityId> {
        val id = productionService.createProduction(request)

        return ApiResponse(id, null, HttpStatus.OK.name)
    }

    @PostMapping(REST_PATH_PRODUCTION_ID + REST_PATH_EVENTS)
    fun createEvent(@PathVariable productionId: Int, @RequestBody request: CreateEventRequest): ApiResponse<String> {
        productionService.createEvent(productionId, request)

        return ApiResponse(status = HttpStatus.OK.name)
    }
}