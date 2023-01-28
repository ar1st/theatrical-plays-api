package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.security.permission.IsAdmin
import aris.thesis.theatricalplaysapi.constants.RestPathConstants
import aris.thesis.theatricalplaysapi.constants.RestPathConstants.REST_PATH_PHOTOS
import aris.thesis.theatricalplaysapi.controllers.actions.impl.PersonActionsImpl
import aris.thesis.theatricalplaysapi.controllers.base.TheatricalPlaysRestController
import aris.thesis.theatricalplaysapi.rest.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionRoleDTO
import aris.thesis.theatricalplaysapi.dtos.request.CreatePersonRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.entities.Image
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(RestPathConstants.REST_BASE_PATH_PEOPLE,
                produces = [MediaType.APPLICATION_JSON_VALUE + RestPathConstants.MEDIA_TYPE_UTF_8])
class PersonController : TheatricalPlaysRestController<PersonActionsImpl>() {

    @PostMapping
    fun createPerson(@RequestBody request: CreatePersonRequest): ApiResponse<EntityId> {
        val id = executor.createPerson(request)

        return ApiResponse(id, null, HttpStatus.OK.name)
    }

    @GetMapping(RestPathConstants.REST_PATH_PERSON_ID)
    fun getById(@PathVariable("personId") personId: Int): ApiResponse<PersonDTO> {
        return executor.getPerson(personId)
    }

    //page minValue = 0, size minValue=1
    //values less than the aforementioned will return the all the elements
    @GetMapping("")
    @IsAdmin
    fun getAllPeople(@RequestParam(required = false) page: Int?,
                     @RequestParam(required = false) size: Int?): ApiResponse<Page<PersonDTO>> {
        return executor.getAllPeople(page ?: -1, size ?: -1)
    }

    @GetMapping(RestPathConstants.REST_PATH_ROLE)
    fun getPeopleByRole(@RequestParam(required = true) value: String,
                        @RequestParam(required = false) page: Int?,
                        @RequestParam(required = false) size: Int?): ApiResponse<Page<PersonDTO>> {
        return executor.getPeopleByRole(value, page ?: -1, size ?: -1)
    }

    @GetMapping(RestPathConstants.REST_PATH_LETTER)
    fun getPeopleByLetter(@RequestParam(required = true) value: String,
                          @RequestParam(required = false) page: Int?,
                          @RequestParam(required = false) size: Int?): ApiResponse<Page<PersonDTO>> {
        return executor.getPeopleByLetter(value, page ?: -1, size ?: -1)
    }

    @GetMapping(RestPathConstants.REST_PATH_PERSON_ID + RestPathConstants.REST_PATH_PRODUCTIONS)
    fun getProductionAndRoleByPersonId(@PathVariable("personId") personId: Int,
                                       @RequestParam(required = false) page: Int?,
                                       @RequestParam(required = false) size: Int?,
                                       response: HttpServletResponse): ApiResponse<Page<ProductionRoleDTO>> {
        return executor.getProductionAndRoleByPersonId(personId, page ?: -1, size ?: -1, response)
    }

    //query: field[:~]value,field2[:~]value2 etc
    //ex q=fullName~μαρια κ,id:1928
    //will search for person where has μαρια in the name AND id=1928
    @GetMapping(RestPathConstants.REST_PATH_SEARCH)
    fun searchPeople(@RequestParam("q") query: String,
                     @RequestParam(required = false) page: Int?,
                     @RequestParam(required = false) size: Int?,
                     response: HttpServletResponse): ApiResponse<Page<PersonDTO>> {
        return executor.searchPeople(query, page ?: -1, size ?: -1, response)
    }

    @GetMapping(RestPathConstants.REST_PATH_PERSON_ID + REST_PATH_PHOTOS)
    fun getPhotosByPersonId(@PathVariable("personId") personId: Int): ApiResponse<Set<Image>> {
        return executor.getPhotosByPersonId(personId)
    }
}