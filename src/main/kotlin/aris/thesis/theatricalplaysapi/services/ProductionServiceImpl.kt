package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.entities.Contribution
import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.repositories.ContributionRepository
import aris.thesis.theatricalplaysapi.repositories.ProductionRepository
import aris.thesis.theatricalplaysapi.services.types.ProductionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductionServiceImpl: ProductionService {
    //repos
    @Autowired
    lateinit var productionRepository: ProductionRepository
    @Autowired
    lateinit var contributionRepository: ContributionRepository

    @Transactional
    override fun getAllProductions(): List<Production> {
        return productionRepository.findAll(Sort.by(Sort.Direction.DESC,"title") )
    }

    override fun getAllProductions(pageable: Pageable): Page<Production> {
        return productionRepository.findAll(pageable)
    }

    override fun getLatestProductions(pageable: Pageable): Page<Production> {
        return productionRepository.findLatestProductions(pageable)
    }

    override fun getById(productionId: Int): Production? {
        return productionRepository.findById(productionId).orElse(null)
    }

    override fun getByContribution(contributionId: Int): Production {
        return productionRepository.findByContribution(contributionId)
    }

    override fun getContributionsByProductionId(productionId: Int): List<Contribution> {
        return contributionRepository.findByProductionID(productionId)
    }

    override fun getProductionBySpec(spec: Specification<Production>, pageable: Pageable): Page<Production> {
        return productionRepository.findAll(spec,pageable)
    }

}