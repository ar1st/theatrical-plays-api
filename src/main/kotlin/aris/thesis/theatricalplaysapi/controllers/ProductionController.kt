package aris.thesis.theatricalplaysapi.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/productions")
class ProductionController {

//    @GetMapping
//    fun findAll(): ApiResponse<List<Production>, String> {
//        return productionExecutor.findAll()
//    }
//
//    @GetMapping("/{productionId}")
//    fun findById(@PathVariable("productionId") productionId: Int,
//                 response: HttpServletResponse): ApiResponse<Production, String> {
//        return productionExecutor.findById(productionId, response)
//    }
//
//    @GetMapping("/{productionId}/people")
//    fun findPeopleByProduction(@PathVariable("productionId") productionId: Int):
//            ApiResponse<List<PersonAndRoleResponse>, String> {
//        return personExecutor.findByProduction(productionId)
//    }
}