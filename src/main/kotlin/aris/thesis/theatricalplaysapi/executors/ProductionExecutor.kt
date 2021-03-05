package aris.thesis.theatricalplaysapi.executors

import aris.thesis.theatricalplaysapi.entities.Production
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.ProductionAndRoleResponse
import aris.thesis.theatricalplaysapi.services.PersonServiceImp
import aris.thesis.theatricalplaysapi.services.ProductionServiceImpl
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletResponse

@Component
class ProductionExecutor {
    @Autowired
    lateinit var productionServiceImpl: ProductionServiceImpl
    @Autowired
    lateinit var personServiceImp: PersonServiceImp

    fun findAll(): ApiResponse<List<Production>, String> {
        return ApiResponse( productionServiceImpl.findAll(), "OK", HttpStatus.OK.name )
    }

    fun findById(productionId: Int, response: HttpServletResponse): ApiResponse<Production, String> {
        val productionToReturn = productionServiceImpl.findById(productionId )
        if ( productionToReturn == null) {
            response.status = HttpStatus.NOT_FOUND.value()
            return ApiResponse(null,"Not Found", HttpStatus.NOT_FOUND.name)
        }

        response.status = HttpStatus.OK.value()
        return ApiResponse(productionToReturn,"OK",HttpStatus.OK.name)
    }

    fun findByPerson(personId: Int, response: HttpServletResponse):
            ApiResponse<List<ProductionAndRoleResponse>, String> {
        val person = personServiceImp.findById(personId)

        if ( person == null) {
            response.status = HttpStatus.NOT_FOUND.value()
            return ApiResponse(null,"No person found with this id.", HttpStatus.NOT_FOUND.name)
        }

        val productions = productionServiceImpl.findByPerson(personId)
        val mapper = jacksonObjectMapper()
        val apiResponse: List<ProductionAndRoleResponse> = mapper.convertValue(productions)

        response.status = HttpStatus.OK.value()
        return ApiResponse(apiResponse,"OK",HttpStatus.OK.name )
    }
}