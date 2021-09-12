package aris.thesis.theatricalplaysapi.controllers.actions.impl

import aris.thesis.theatricalplaysapi.controllers.actions.ActionExecutor
import aris.thesis.theatricalplaysapi.controllers.actions.Actions
import aris.thesis.theatricalplaysapi.dtos.EventVenueDTO
import aris.thesis.theatricalplaysapi.dtos.PersonRoleDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.exceptions.error.never
import aris.thesis.theatricalplaysapi.exceptions.error.notFound
import aris.thesis.theatricalplaysapi.exceptions.error.wrongQuery
import aris.thesis.theatricalplaysapi.parsers.ProductionSpecificationBuilderParser
import aris.thesis.theatricalplaysapi.rest.ApiResponse
import aris.thesis.theatricalplaysapi.services.proto.ModelServiceConsumer6
import aris.thesis.theatricalplaysapi.services.types.*
import aris.thesis.theatricalplaysapi.utils.asPageProductionDTO
import aris.thesis.theatricalplaysapi.utils.asProductionDTO
import org.springframework.data.domain.Page
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletResponse

@Component
@Suppress("unused")
class ProductionActionsImpl : ActionExecutor<Actions.Production>, ModelServiceConsumer6<ProductionService, PersonService, RoleService, ImageService, EventService, VenueService>() {

    fun getAllProductions(page: Int, size: Int): ApiResponse<Page<ProductionDTO>, String> {
        val productions = firstService.getAllProductions(page, size)

        return ApiResponse(productions.asPageProductionDTO(), null, HttpStatus.OK.name)
    }

    fun getProduction(productionId: Int, response: HttpServletResponse): ApiResponse<ProductionDTO, String> {
        val production = firstService.getById(productionId) ?: notFound("Production", productionId.toString())

        return ApiResponse(production.asProductionDTO(), null, HttpStatus.OK.name)
    }

    fun getLatestProductions(page: Int, size: Int): ApiResponse<Page<ProductionDTO>, String> {
        val latestProductions = firstService.getLatestProductions(page, size)

        return ApiResponse(latestProductions.asPageProductionDTO(), null, HttpStatus.OK.name)
    }

    fun searchProduction(query: String, page: Int, size: Int,
                         response: HttpServletResponse): ApiResponse<Page<ProductionDTO>, String> {
        val parser = ProductionSpecificationBuilderParser()
        val builder = parser.parse(query)

        val spec: Specification<Production> = builder.build() ?: wrongQuery()

        val productionsBySpec = firstService.getProductionBySpec(spec, page, size)
        return ApiResponse(productionsBySpec.asPageProductionDTO(), null, HttpStatus.OK.name)
    }

    fun getPeopleByProductionId(productionId: Int): ApiResponse<List<PersonRoleDTO>, String> {
        firstService.getById(productionId) ?: notFound("Production", productionId.toString())

        val contributions = firstService.getContributionsByProductionId(productionId)
        val allImages = fourthService.getAll()

        val dtoToReturn = contributions.map { contribution ->
            val person = secondService.getByContributionId(contribution.id ?: never()) ?: never()
            val role = thirdService.getByContributionId(contribution.id ?: never()) ?: never()
            val image = allImages.firstOrNull { it.personId == person.id }
            PersonRoleDTO(person, role, image)
        }

        return ApiResponse(dtoToReturn, null, HttpStatus.OK.name)
    }

    fun getEventsAndVenuesByProduction(productionId: Int): ApiResponse<List<EventVenueDTO>, String> {
        firstService.getById(productionId) ?: notFound("Production", productionId.toString())

        val events = fifthService.getEventsByProductionId(productionId)

        val dtoToReturn = events.map {
            val venue = sixthService.getVenueByEventId(it.id ?: never())

            EventVenueDTO(it, venue)
        }

        return ApiResponse(dtoToReturn, null, HttpStatus.OK.name)
    }
}