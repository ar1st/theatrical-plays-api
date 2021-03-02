package aris.thesis.theatricalplaysapi.executors

import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.responses.ApiResponse
import aris.thesis.theatricalplaysapi.responses.ProductionAndRoleResponse
import aris.thesis.theatricalplaysapi.services.PersonService
import aris.thesis.theatricalplaysapi.services.ProductionService
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletResponse

@Component
class ProductionExecutor {
    @Autowired
    lateinit var productionService: ProductionService
    @Autowired
    lateinit var personService: PersonService

    fun findAll(): ApiResponse<List<Production>, String> {
        return ApiResponse( productionService.findAll(), "OK", HttpStatus.OK.value() )
    }

    fun findById(productionId: Int, response: HttpServletResponse): ApiResponse<Production, String> {
        val productionToReturn = productionService.findById(productionId )
        if ( productionToReturn == null) {
            response.status = HttpStatus.NOT_FOUND.value()
            return ApiResponse(null,"Not Found", HttpStatus.NOT_FOUND.value())
        }

        response.status = HttpStatus.OK.value()
        return ApiResponse(productionToReturn,"OK",HttpStatus.OK.value())
    }

    fun findByPerson(personId: Int, response: HttpServletResponse):
            ApiResponse<List<ProductionAndRoleResponse>, String> {
        val person = personService.findById(personId)

        if ( person == null) {
            response.status = HttpStatus.NOT_FOUND.value()
            return ApiResponse(null,"No person found with this id.", HttpStatus.NOT_FOUND.value())
        }

        val productions = productionService.findByPerson(personId)
        val mapper = jacksonObjectMapper()
        val apiResponse: List<ProductionAndRoleResponse> = mapper.convertValue(productions)

        response.status = HttpStatus.OK.value()
        return ApiResponse(apiResponse,"OK",HttpStatus.OK.value() )
    }
}