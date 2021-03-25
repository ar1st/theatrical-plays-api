package aris.thesis.theatricalplaysapi.controllers.actions.def

import aris.thesis.theatricalplaysapi.controllers.actions.ActionExecutor
import aris.thesis.theatricalplaysapi.controllers.actions.Actions
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import org.springframework.data.domain.Page
import javax.servlet.http.HttpServletResponse

interface ProductionActions: ActionExecutor<Actions.Production> {

    fun getProduction(productionId: Int, response: HttpServletResponse): ApiResponse<ProductionDTO, String>

    fun getProductions(page: Int, size: Int): ApiResponse<Page<ProductionDTO>, String>
}