package aris.thesis.theatricalplaysapi.controllers.actions.impl

import aris.thesis.theatricalplaysapi.controllers.actions.def.PersonActions
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PaginatedResult
import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionRoleDTO
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.exceptions.error.never
import aris.thesis.theatricalplaysapi.exceptions.error.notFound
import aris.thesis.theatricalplaysapi.exceptions.error.wrongQuery
import aris.thesis.theatricalplaysapi.parsers.PersonSpecificationBuilderParser
import aris.thesis.theatricalplaysapi.services.proto.ModelServiceConsumer3
import aris.thesis.theatricalplaysapi.services.types.PersonService
import aris.thesis.theatricalplaysapi.services.types.ProductionService
import aris.thesis.theatricalplaysapi.services.types.RoleService
import aris.thesis.theatricalplaysapi.utils.paginated
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletResponse


@Component
@Suppress("unused")
class PersonActionsImpl : PersonActions, ModelServiceConsumer3<PersonService, ProductionService, RoleService>() {

    override fun getPerson(personId: Int): ApiResponse<PersonDTO, String> {

        val person = firstService.findById(personId) ?: notFound("Person", personId.toString())

        return ApiResponse(PersonDTO(person), null, HttpStatus.OK.name)
    }

    override fun getAllPeople(page: Int, size: Int): ApiResponse<Page<PersonDTO>, String> {
        val paginatedResult = if (page >= 0 && size > 0)
            firstService.findAllPeople(PageRequest.of(page, size))
        else
            firstService.findAllPeople(Pageable.unpaged())

        return ApiResponse(paginatedResult.map { PersonDTO(it) }, null, HttpStatus.OK.name)
    }

    override fun getProductionAndRoleByPersonId(
        personId: Int,
        page: Int,
        size: Int,
        response: HttpServletResponse
    ): ApiResponse<Page<ProductionRoleDTO>, String> {
        firstService.findById(personId) ?: notFound("Person", personId.toString())

        val pagedResult = if (page >= 0 && size > 0)
            firstService.findContributionsByPersonId(personId, PageRequest.of(page, size))
        else
            firstService.findContributionsByPersonId(personId, Pageable.unpaged())

        val dtoToReturn = pagedResult.map {
            val production = secondService.findByContribution(it.id ?: never()) ?: never()
            val role = thirdService.findByContribution(it.id ?: never()) ?: never()

            ProductionRoleDTO(production, role)
        }

        return ApiResponse(dtoToReturn, null, HttpStatus.OK.name)
    }

    override fun searchPeople(
        query: String,
        page: Int,
        size: Int,
        response: HttpServletResponse
    ): ApiResponse<Page<PersonDTO>, String> {
        val parser = PersonSpecificationBuilderParser()
        val builder = parser.parse(query)

        val spec: Specification<Person> = builder.build() ?: wrongQuery()

        val pagedResult = if (page >= 0 && size > 0)
            firstService.findPeopleBySpec(spec, PageRequest.of(page, size))
        else
            firstService.findPeopleBySpec(spec, Pageable.unpaged())
        return ApiResponse(pagedResult.map { PersonDTO(it) }, null, HttpStatus.OK.name)
    }

}