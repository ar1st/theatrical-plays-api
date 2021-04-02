package aris.thesis.theatricalplaysapi.controllers.actions.def

import aris.thesis.theatricalplaysapi.controllers.actions.ActionExecutor
import aris.thesis.theatricalplaysapi.controllers.actions.Actions
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionRoleDTO
import org.springframework.data.domain.Page
import javax.servlet.http.HttpServletResponse

interface PersonActions: ActionExecutor<Actions.Person> {

    fun getPerson(personId: Int): ApiResponse<PersonDTO, String>

    fun getAllPeople(page: Int, size : Int): ApiResponse<Page<PersonDTO>, String>

    fun getPeopleByRole(value: String, page: Int, size: Int): ApiResponse<Page<PersonDTO>, String>

    fun getProductionAndRoleByPersonId( personId: Int,page: Int, size: Int,response: HttpServletResponse): ApiResponse<Page<ProductionRoleDTO>,String>

    fun searchPeople(query: String, page: Int, size: Int, response: HttpServletResponse): ApiResponse<Page<PersonDTO>,String>

}