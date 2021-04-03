package aris.thesis.theatricalplaysapi.controllers.actions.impl

import aris.thesis.theatricalplaysapi.controllers.actions.def.PersonActions
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionRoleDTO
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.exceptions.error.never
import aris.thesis.theatricalplaysapi.exceptions.error.notFound
import aris.thesis.theatricalplaysapi.exceptions.error.wrongQuery
import aris.thesis.theatricalplaysapi.parsers.PersonSpecificationBuilderParser
import aris.thesis.theatricalplaysapi.services.proto.ModelServiceConsumer4
import aris.thesis.theatricalplaysapi.services.types.ImageService
import aris.thesis.theatricalplaysapi.services.types.PersonService
import aris.thesis.theatricalplaysapi.services.types.ProductionService
import aris.thesis.theatricalplaysapi.services.types.RoleService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletResponse


@Component
@Suppress("unused")
class PersonActionsImpl : PersonActions, ModelServiceConsumer4<PersonService, ProductionService, RoleService, ImageService>() {

    override fun getPerson(personId: Int): ApiResponse<PersonDTO, String> {

        val person = firstService.getById(personId) ?: notFound("Person", personId.toString())
        val image = fourthService.getById(personId)

        return ApiResponse(PersonDTO(person,image), null, HttpStatus.OK.name)
    }

    override fun getAllPeople(page: Int, size: Int): ApiResponse<Page<PersonDTO>, String> {
        val paginatedResult = if (page >= 0 && size > 0)
                                    firstService.getAllPeople(PageRequest.of(page, size))
                                else
                                    firstService.getAllPeople(Pageable.unpaged())

        val allImages = fourthService.getAll()

        val dtoToReturn = paginatedResult.map { person ->
            PersonDTO(person, allImages.firstOrNull{ it.id == person.id })
        }

        return ApiResponse( dtoToReturn , null, HttpStatus.OK.name)
    }

    override fun getPeopleByRole(value: String, page: Int, size: Int): ApiResponse<Page<PersonDTO>, String> {
        val paginatedResult = if (page >= 0 && size > 0)
                            firstService.getPeopleByRole(value,PageRequest.of(page, size))
                        else
                            firstService.getPeopleByRole(value,Pageable.unpaged())

        val allImages = fourthService.getAll()

        val dtoToReturn = paginatedResult.map { person ->
            PersonDTO(person, allImages.firstOrNull{ it.id == person.id })
        }

        return ApiResponse( dtoToReturn , null, HttpStatus.OK.name)
    }


    override fun getProductionAndRoleByPersonId(
        personId: Int,
        page: Int,
        size: Int,
        response: HttpServletResponse
    ): ApiResponse<Page<ProductionRoleDTO>, String> {
        firstService.getById(personId) ?: notFound("Person", personId.toString())

        val pagedResult = if (page >= 0 && size > 0)
            firstService.getContributionsByPersonId(personId, PageRequest.of(page, size))
        else
            firstService.getContributionsByPersonId(personId, Pageable.unpaged())

        val dtoToReturn = pagedResult.map {
            val production = secondService.getByContribution(it.id ?: never()) ?: never()
            val role = thirdService.getByContributionId(it.id ?: never()) ?: never()

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

        val paginatedResult = if (page >= 0 && size > 0)
            firstService.getPeopleBySpec(spec, PageRequest.of(page, size))
        else
            firstService.getPeopleBySpec(spec, Pageable.unpaged())

        val allImages = fourthService.getAll()

        val dtoToReturn = paginatedResult.map { person ->
            PersonDTO(person, allImages.firstOrNull{ it.id == person.id })
        }
        return ApiResponse(dtoToReturn, null, HttpStatus.OK.name)
    }

}