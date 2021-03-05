package aris.thesis.theatricalplaysapi.executors

import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonAndRoleResponse
import aris.thesis.theatricalplaysapi.services.PersonServiceImp
import aris.thesis.theatricalplaysapi.services.ProductionServiceImpl
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class PersonExecutor {
    @Autowired
    lateinit var personServiceImp: PersonServiceImp
    @Autowired
    lateinit var productionServiceImpl: ProductionServiceImpl

    fun findByProduction(productionId: Int): ApiResponse<List<PersonAndRoleResponse>, String> {
        val production = productionServiceImpl.findById(productionId)
            ?: return ApiResponse(null,"No production found with this id.", HttpStatus.NOT_FOUND.name )

        val people = personServiceImp.findByProduction(productionId)
        val mapper = jacksonObjectMapper()
        val response: List<PersonAndRoleResponse> = mapper.convertValue(people)

        return ApiResponse(response,"OK",HttpStatus.OK.name )
    }
}