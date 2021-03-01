package aris.thesis.theratricalplaysapi.services

import aris.thesis.theratricalplaysapi.entities.Production
import aris.thesis.theratricalplaysapi.repositories.ProductionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductionService {
    @Autowired
    lateinit var productionRepository: ProductionRepository

    @Transactional
    fun findAll(): List<Production> {
        return productionRepository.findAll()
    }

    fun findById(productionId: Int): Production? {
        return productionRepository.findById(productionId).orElse(null)
    }
}