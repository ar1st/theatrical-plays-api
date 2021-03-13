package aris.thesis.theatricalplaysapi.controllers.actions.def

import aris.thesis.theatricalplaysapi.controllers.actions.ActionExecutor
import aris.thesis.theatricalplaysapi.controllers.actions.Actions
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionRoleDTO
import javax.servlet.http.HttpServletResponse

interface PersonActions: ActionExecutor<Actions.Person> {

    fun getPerson(personId: Int): ApiResponse<PersonDTO, String>

    fun getAllPeople(page: Int, size : Int): ApiResponse<List<PersonDTO>, String>

    fun getProductionAndRoleByPersonId( personId: Int,page: Int, size: Int,response: HttpServletResponse): ApiResponse<List<ProductionRoleDTO>,String>

    fun searchPeople(query: String, response: HttpServletResponse): ApiResponse<List<PersonDTO>,String>

}