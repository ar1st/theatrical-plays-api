package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.curators.DataCurator
import aris.thesis.theatricalplaysapi.dtos.request.CreateProductionRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.entities.Contribution
import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.repositories.ContributionRepository
import aris.thesis.theatricalplaysapi.repositories.ProductionRepository
import aris.thesis.theatricalplaysapi.services.types.ProductionService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductionServiceImpl(
    val productionRepository: ProductionRepository,
    val contributionRepository: ContributionRepository
) : ProductionService {

    override fun getAllProductions(page: Int, size: Int): Page<Production> {
        return if (page >= 0 && size > 0) {
            productionRepository.findAll(PageRequest.of(page, size))
        } else {
            productionRepository.findAll(Pageable.unpaged())
        }
    }

    override fun getLatestProductions(page: Int, size: Int): Page<Production> {
        return if (page >= 0 && size > 0) {
            productionRepository.findLatestProductions(PageRequest.of(page, size))
        } else {
            productionRepository.findLatestProductions(Pageable.unpaged())
        }
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

    override fun getProductionBySpec(spec: Specification<Production>, page: Int, size: Int): Page<Production> {
        return if (page >= 0 && size > 0) {
            productionRepository.findAll(spec, PageRequest.of(page, size))
        } else {
            productionRepository.findAll(spec, Pageable.unpaged())
        }
    }

    override fun getProductionsByVenueId(venueId: Int, page: Int, size: Int): Page<Production> {
        return if (page >= 0 && size > 0) {
            productionRepository.findByVenueId(venueId, PageRequest.of(page, size))
        } else {
            productionRepository.findByVenueId(venueId, Pageable.unpaged())
        }
    }

    @Transactional
    override fun createProduction(request: CreateProductionRequest): EntityId {
        val productionByTitle = productionRepository.findByTitle(request.title)
        val id = EntityId()

        if (productionByTitle == null) {
            DataCurator.curateProductionTitle(request.title)
            val production = productionRepository.save(
                Production(
                    request.title,
                    request.description,
                    request.url,
                    request.producer,
                    request.mediaURL,
                    request.duration,
                    request.organizerId
                )
            )
            id.newId = production.id
        } else {
            id.existingId = productionByTitle.id
            productionByTitle.description = request.description
            productionByTitle.url = request.url
            productionByTitle.producer = request.producer
            productionByTitle.mediaURL = request.mediaURL
            productionByTitle.duration = request.duration
            productionByTitle.organizerID = request.organizerId
        }

        return id
    }
}