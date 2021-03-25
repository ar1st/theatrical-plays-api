package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.services.proto.ModelService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductionService: ModelService {
    fun findAll(): List<Production>
    fun findAll(pageable: Pageable): Page<Production>
    fun findById(productionId: Int): Production?
    fun findByContribution(contributionId: Int): Production?
}