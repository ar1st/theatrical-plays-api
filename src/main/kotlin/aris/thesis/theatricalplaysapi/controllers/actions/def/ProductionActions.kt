package aris.thesis.theatricalplaysapi.controllers.actions.def

import aris.thesis.theatricalplaysapi.controllers.actions.ActionExecutor
import aris.thesis.theatricalplaysapi.controllers.actions.Actions
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonRoleDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import org.springframework.data.domain.Page
import javax.servlet.http.HttpServletResponse

interface ProductionActions: ActionExecutor<Actions.Production> {

    fun getAllProductions(page: Int, size: Int): ApiResponse<Page<ProductionDTO>, String>

    fun getProduction(productionId: Int, response: HttpServletResponse): ApiResponse<ProductionDTO, String>
    fun getPeopleByProductionId(productionId: Int): ApiResponse<List<PersonRoleDTO>, String>

    fun searchProduction(query: String, page: Int, size: Int, response: HttpServletResponse): ApiResponse<Page<ProductionDTO>, String>
}