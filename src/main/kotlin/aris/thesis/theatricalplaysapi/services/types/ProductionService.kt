package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.entities.Contribution
import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.services.proto.ModelService
import org.springframework.data.domain.Page
import org.springframework.data.jpa.domain.Specification

interface ProductionService: ModelService {
    fun getAllProductions(page: Int, size: Int): Page<Production>
    fun getLatestProductions(page: Int, size: Int): Page<Production>
    fun getById(productionId: Int): Production?
    fun getByContribution(contributionId: Int): Production?
    fun getContributionsByProductionId(productionId: Int): List<Contribution>

    fun getProductionBySpec(spec: Specification<Production>, page: Int, size: Int): Page<Production>
    fun getProductionsByVenueId(venueId: Int, page: Int, size: Int): Page<Production>
}