package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.dtos.request.CreateContributionRequest
import aris.thesis.theatricalplaysapi.dtos.request.CreateEventRequest
import aris.thesis.theatricalplaysapi.dtos.request.CreateProductionRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
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

    fun createProduction(request: CreateProductionRequest): EntityId

    fun createEvent(productionId: Int, request: CreateEventRequest)

    fun deleteEvent(productionId: Int, eventId: Int)

    fun createContribution(productionId: Int, request: CreateContributionRequest)

    fun deleteContribution(productionId: Int, contributionId: Int)
}