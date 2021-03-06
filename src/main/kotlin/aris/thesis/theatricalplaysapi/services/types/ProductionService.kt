package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.services.proto.ModelService

interface ProductionService: ModelService {
    fun findAll(): List<Production>
    fun findById(productionId: Int): Production?
    fun findByContribution(contributionId: Int): Production?
}