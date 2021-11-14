package aris.thesis.theatricalplaysapi.controllers.actions.impl

import aris.thesis.theatricalplaysapi.controllers.actions.ActionExecutor
import aris.thesis.theatricalplaysapi.controllers.actions.Actions
import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionRoleDTO
import aris.thesis.theatricalplaysapi.entities.Image
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.exceptions.error.never
import aris.thesis.theatricalplaysapi.exceptions.error.notFound
import aris.thesis.theatricalplaysapi.exceptions.error.wrongQuery
import aris.thesis.theatricalplaysapi.parsers.PersonSpecificationBuilderParser
import aris.thesis.theatricalplaysapi.rest.ApiResponse
import aris.thesis.theatricalplaysapi.services.proto.ModelServiceConsumer4
import aris.thesis.theatricalplaysapi.services.types.ImageService
import aris.thesis.theatricalplaysapi.services.types.PersonService
import aris.thesis.theatricalplaysapi.services.types.ProductionService
import aris.thesis.theatricalplaysapi.services.types.RoleService
import aris.thesis.theatricalplaysapi.utils.asPersonDTO
import org.springframework.data.domain.Page
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletResponse

@Component
@Suppress("unused")
class PersonActionsImpl : ActionExecutor<Actions.Person>,
                            ModelServiceConsumer4<PersonService, ProductionService, RoleService, ImageService>() {

    fun getPerson(personId: Int): ApiResponse<PersonDTO, String> {
        val person = firstService.getById(personId) ?: notFound("Person", personId.toString())
        val  images= fourthService.getByPersonId(personId)

        return ApiResponse( person.asPersonDTO(images), null, HttpStatus.OK.name)
    }

    fun getAllPeople(page: Int, size: Int): ApiResponse<Page<PersonDTO>, String> {
        val people = firstService.getAllPeople(page, size)

        val allImages = fourthService.getAll()

        val dtoToReturn = people.map { person ->
            PersonDTO(person, allImages.filter { it.personId == person.id }.toSet())
        }

        return ApiResponse(dtoToReturn, null, HttpStatus.OK.name)
    }

    fun getPeopleByRole(value: String, page: Int, size: Int): ApiResponse<Page<PersonDTO>, String> {
        val peopleByRole = firstService.getPeopleByRole(value, page, size)

        val allImages = fourthService.getAll()

        val dtoToReturn = peopleByRole.map { person ->
            PersonDTO(person, allImages.filter { it.personId == person.id }.toSet())
        }

        return ApiResponse(dtoToReturn, null, HttpStatus.OK.name)
    }

    fun getPeopleByLetter(value: String, page: Int, size: Int): ApiResponse<Page<PersonDTO>, String> {
        val peopleByLetter = firstService.getPeopleByLetter("$value%", page, size)


        val allImages = fourthService.getAll()

        val dtoToReturn = peopleByLetter.map { person ->
            PersonDTO(person, allImages.filter { it.personId == person.id }.toSet())
        }

        return ApiResponse(dtoToReturn, null, HttpStatus.OK.name)
    }

    fun getProductionAndRoleByPersonId(
        personId: Int, page: Int, size: Int,
        response: HttpServletResponse
    ): ApiResponse<Page<ProductionRoleDTO>, String> {
        firstService.getById(personId) ?: notFound("Person", personId.toString())

        val contributions = firstService.getContributionsByPersonId(personId, page, size)

        val dtoToReturn = contributions.map {
            val production = secondService.getByContribution(it.id ?: never()) ?: never()
            val role = thirdService.getByContributionId(it.id ?: never()) ?: never()

            ProductionRoleDTO(production, role)
        }

        return ApiResponse(dtoToReturn, null, HttpStatus.OK.name)
    }

    fun searchPeople(
        query: String, page: Int, size: Int,
        response: HttpServletResponse
    ): ApiResponse<Page<PersonDTO>, String> {
        val parser = PersonSpecificationBuilderParser()
        val builder = parser.parse(query)

        val spec: Specification<Person> = builder.build() ?: wrongQuery()

        val peopleBySpec = firstService.getPeopleBySpec(spec, page, size)

        val allImages = fourthService.getAll()

        val dtoToReturn = peopleBySpec.map { person ->
            PersonDTO(person, allImages.filter { it.personId == person.id }.toSet())
        }
        return ApiResponse(dtoToReturn, null, HttpStatus.OK.name)
    }

    fun getPhotosByPersonId(personId: Int): ApiResponse<Set<Image>, String> {
        return ApiResponse(firstService.getPhotosByPersonId(personId), null, HttpStatus.OK.name)
    }
}