package aris.thesis.theratricalplaysapi.controllers

import aris.thesis.theratricalplaysapi.entities.Production
import aris.thesis.theratricalplaysapi.services.ProductionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/productions")
class ProductionController {
    @Autowired
    lateinit var productionService: ProductionService

    @GetMapping
    fun findAll(): List<Production> {
        return productionService.findAll()
    }

    @GetMapping("/{productionId}")
    fun findById(@PathVariable("productionId") productionId: Int): Production? {
        return productionService.findById(productionId)
    }
}