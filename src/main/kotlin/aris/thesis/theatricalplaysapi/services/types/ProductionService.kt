package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.entities.Contribution
import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.services.proto.ModelService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductionService: ModelService {
    fun getAllProductions(): List<Production>
    fun getAllProductions(pageable: Pageable): Page<Production>
    fun getById(productionId: Int): Production?
    fun getByContribution(contributionId: Int): Production?
    fun getContributionsByProductionId(productionId: Int): List<Contribution>
}