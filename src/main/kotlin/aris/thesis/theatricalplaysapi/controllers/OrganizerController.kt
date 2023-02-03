package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.constants.RestPathConstants
import aris.thesis.theatricalplaysapi.dtos.request.CreateOrganizerRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.rest.ApiResponse
import aris.thesis.theatricalplaysapi.security.permission.IsAdmin
import aris.thesis.theatricalplaysapi.services.types.OrganizerService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    RestPathConstants.REST_PATH_ORGANIZERS,
    produces = [MediaType.APPLICATION_JSON_VALUE + RestPathConstants.MEDIA_TYPE_UTF_8])
class OrganizerController(val organizerService: OrganizerService) {

    @IsAdmin
    @PostMapping
    fun createOrganizer(@RequestBody request: CreateOrganizerRequest): ApiResponse<EntityId> {
        val id = organizerService.creteOrganizer(request)

        return ApiResponse(id, null, HttpStatus.OK.name)
    }

    @IsAdmin
    @DeleteMapping("/{venueId}")
    fun deleteVenue(@PathVariable venueId: Int): ApiResponse<String?> {
        organizerService.deleteOrganizer(venueId)

        return ApiResponse(status = HttpStatus.OK.name)
    }

}