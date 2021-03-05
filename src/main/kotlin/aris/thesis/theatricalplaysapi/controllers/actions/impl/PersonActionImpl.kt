package aris.thesis.theatricalplaysapi.controllers.actions.impl

import aris.thesis.theatricalplaysapi.controllers.actions.def.PersonActions
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.exceptions.notFound
import aris.thesis.theatricalplaysapi.services.proto.ModelServiceConsumer
import aris.thesis.theatricalplaysapi.services.types.PersonService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class PersonActionImpl: PersonActions, ModelServiceConsumer<PersonService>() {

    override fun getPerson(personId: Int): ApiResponse<PersonDTO, String> {
        val person = service.findById(personId) ?: notFound("Person",personId.toString())

        return ApiResponse( PersonDTO(person),null,HttpStatus.OK.name)
    }
}