package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.repositories.ProductionRepository
import aris.thesis.theatricalplaysapi.services.types.ProductionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductionServiceImpl: ProductionService {
    //repos
    @Autowired
    lateinit var productionRepository: ProductionRepository

    @Transactional
    override fun findAll(): List<Production> {
        return productionRepository.findAll()
    }

    override fun findById(productionId: Int): Production? {
        return productionRepository.findById(productionId).orElse(null)
    }

    override fun findByContribution(contributionId: Int): Production {
        return productionRepository.findByContribution(contributionId)
    }

}