package aris.thesis.theatricalplaysapi.controllers.actions.impl

import aris.thesis.theatricalplaysapi.controllers.actions.def.PersonActions
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionRoleDTO
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.exceptions.error.never
import aris.thesis.theatricalplaysapi.exceptions.error.notFound
import aris.thesis.theatricalplaysapi.services.proto.ModelServiceConsumer3
import aris.thesis.theatricalplaysapi.services.types.PersonService
import aris.thesis.theatricalplaysapi.services.types.ProductionService
import aris.thesis.theatricalplaysapi.services.types.RoleService
import aris.thesis.theatricalplaysapi.specifications.PersonSpecificationBuilder
import aris.thesis.theatricalplaysapi.utils.paginated
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.servlet.http.HttpServletResponse


@Component
@Suppress("unused")
class PersonActionImpl: PersonActions, ModelServiceConsumer3<PersonService,ProductionService,RoleService>() {

    override fun getPerson(personId: Int): ApiResponse<PersonDTO, String> {

        val person = firstService.findById(personId) ?: notFound("Person",personId.toString())

        return ApiResponse( PersonDTO(person),null,HttpStatus.OK.name)
    }

    override fun getAllPeople(page: Int, size: Int): ApiResponse<List<PersonDTO>, String> {

        val people = firstService.findAllPeople().paginated(page,size)

        return ApiResponse( people.map { PersonDTO(it) },null,HttpStatus.OK.name)
    }

    override fun getProductionAndRoleByPersonId(
        personId: Int,
        page: Int,
        size: Int,
        response: HttpServletResponse
    ): ApiResponse<List<ProductionRoleDTO>, String> {
        firstService.findById(personId) ?: notFound("Person", personId.toString())

        val contributions = firstService.findContributionsByPersonId(personId)

        val dtoToReturn = contributions.map {
            val production = secondService.findByContribution(it.id ?: never()) ?: never()
            val role = thirdService.findByContribution(it.id ?: never()) ?: never()

            ProductionRoleDTO(production,role)
        }.paginated(page,size)

        return ApiResponse( dtoToReturn, null,HttpStatus.OK.name)
    }

    override fun searchPeople(query: String, response: HttpServletResponse): ApiResponse<List<PersonDTO>, String> {
        //move this to parser
        val builder = PersonSpecificationBuilder()
        val pattern: Pattern = Pattern.compile("([\\w ]*[^\\W_][\\w ]*?)([:<>!~])([\\w ]*[^\\W_][\\w ]*?),", Pattern.UNICODE_CHARACTER_CLASS)
        val matcher: Matcher = pattern.matcher("$query,")
        while (matcher.find()) {
            val g1 = matcher.group(1)
            val g2 = matcher.group(2)
            val g3 = matcher.group(3)

            builder.with(g1,g2,g3)
        }

        //todo check if builder.build returns null
        val spec: Specification<Person> = builder.build()

        return ApiResponse(firstService.findAllPeople(spec).map { PersonDTO(it) },null,HttpStatus.OK.name)
    }

}