package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.executors.PersonExecutor
import aris.thesis.theatricalplaysapi.responses.ApiResponse
import aris.thesis.theatricalplaysapi.executors.ProductionExecutor
import aris.thesis.theatricalplaysapi.responses.PersonAndRoleResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/productions")
class ProductionController {
    @Autowired
    lateinit var productionExecutor: ProductionExecutor
    @Autowired
    lateinit var personExecutor: PersonExecutor

    @GetMapping
    fun findAll(): ApiResponse<List<Production>, String> {
        return productionExecutor.findAll()
    }

    @GetMapping("/{productionId}")
    fun findById(@PathVariable("productionId") productionId: Int,
                 response: HttpServletResponse): ApiResponse<Production, String> {
        return productionExecutor.findById(productionId, response)
    }

    @GetMapping("/{productionId}/people")
    fun findPeopleByProduction(@PathVariable("productionId") productionId: Int):
            ApiResponse<List<PersonAndRoleResponse>, String> {
        return personExecutor.findByProduction(productionId)
    }
}