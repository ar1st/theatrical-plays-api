package aris.thesis.theatricalplaysapi.executors

import aris.thesis.theatricalplaysapi.responses.ApiResponse
import aris.thesis.theatricalplaysapi.responses.PersonAndRoleResponse
import aris.thesis.theatricalplaysapi.services.PersonService
import aris.thesis.theatricalplaysapi.services.ProductionService
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class PersonExecutor {
    @Autowired
    lateinit var personService: PersonService
    @Autowired
    lateinit var productionService: ProductionService

    fun findByProduction(productionId: Int): ApiResponse<List<PersonAndRoleResponse>, String> {
        val production = productionService.findById(productionId)
            ?: return ApiResponse(null,"No production found with this id.", HttpStatus.NOT_FOUND.value())

        val people = personService.findByProduction(productionId)
        val mapper = jacksonObjectMapper()
        val response: List<PersonAndRoleResponse> = mapper.convertValue(people)

        return ApiResponse(response,"OK",HttpStatus.OK.value() )
    }
}