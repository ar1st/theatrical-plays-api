package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.controllers.actions.def.ProductionActions
import aris.thesis.theatricalplaysapi.controllers.base.TheatricalPlaysRestController
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonRoleDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/productions")
class ProductionController: TheatricalPlaysRestController<ProductionActions>() {

    @GetMapping("")
    fun getAll(@RequestParam(required = false) page: Int?,
               @RequestParam(required = false) size: Int?,): ApiResponse<Page<ProductionDTO>, String> {
        return executor.getAllProductions(page?: -1, size?: -1)
    }

    @GetMapping("/{productionId}")
    fun getById(@PathVariable("productionId") productionId: Int,
                response: HttpServletResponse): ApiResponse<ProductionDTO, String> {
        return executor.getProduction(productionId, response)
    }

    @GetMapping("/{productionId}/people")
    fun findPeopleByProduction(@PathVariable("productionId") productionId: Int):
            ApiResponse<List<PersonRoleDTO>, String> {
        return executor.getPeopleByProductionId(productionId)
    }
}