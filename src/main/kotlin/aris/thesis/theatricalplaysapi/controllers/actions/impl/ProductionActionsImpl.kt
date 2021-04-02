package aris.thesis.theatricalplaysapi.controllers.actions.impl

import aris.thesis.theatricalplaysapi.controllers.actions.def.ProductionActions
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonRoleDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionDTO
import aris.thesis.theatricalplaysapi.exceptions.error.never
import aris.thesis.theatricalplaysapi.exceptions.error.notFound
import aris.thesis.theatricalplaysapi.services.proto.ModelServiceConsumer3
import aris.thesis.theatricalplaysapi.services.types.PersonService
import aris.thesis.theatricalplaysapi.services.types.ProductionService
import aris.thesis.theatricalplaysapi.services.types.RoleService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletResponse

@Component
@Suppress("unused")
class ProductionActionsImpl: ProductionActions, ModelServiceConsumer3<ProductionService,PersonService,RoleService>() {

    override fun getProduction(productionId: Int, response: HttpServletResponse): ApiResponse<ProductionDTO, String> {
        val production = firstService.getById(productionId) ?: notFound("Production",productionId.toString())

        return ApiResponse( ProductionDTO(production),null, HttpStatus.OK.name)
    }

    override fun getAllProductions(page: Int, size: Int): ApiResponse<Page<ProductionDTO>, String> {
        val paginatedResult = if (page >= 0 && size > 0)
            firstService.getAllProductions(PageRequest.of(page, size))
        else
            firstService.getAllProductions( Pageable.unpaged())


        return ApiResponse(paginatedResult.map { ProductionDTO(it) }, null, HttpStatus.OK.name)
    }

    override fun getPeopleByProductionId(productionId: Int): ApiResponse<List<PersonRoleDTO>, String> {
        firstService.getById(productionId) ?: notFound("Production",productionId.toString())


        val contributions = firstService.getContributionsByProductionId(productionId)


        val dtoToReturn = contributions.map {
            val person = secondService.getByContributionId(it.id ?: never()) ?: never()
            val role = thirdService.getByContributionId(it.id ?: never()) ?: never()

            PersonRoleDTO(person, role)
        }

        return ApiResponse(dtoToReturn, null, HttpStatus.OK.name)
    }
}