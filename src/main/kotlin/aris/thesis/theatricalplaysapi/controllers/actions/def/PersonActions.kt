package aris.thesis.theatricalplaysapi.controllers.actions.def

import aris.thesis.theatricalplaysapi.controllers.actions.ActionExecutor
import aris.thesis.theatricalplaysapi.controllers.actions.Actions
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonDTO

interface PersonActions: ActionExecutor<Actions.Person> {

    fun getPerson(personId: Int): ApiResponse<PersonDTO, String>

    fun getAllPeople(): ApiResponse<List<PersonDTO>, String>


}