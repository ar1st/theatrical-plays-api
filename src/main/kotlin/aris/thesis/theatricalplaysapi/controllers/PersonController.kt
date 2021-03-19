package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.controllers.actions.def.PersonActions
import aris.thesis.theatricalplaysapi.controllers.base.TheatricalPlaysRestController
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionRoleDTO
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/people")
class PersonController: TheatricalPlaysRestController<PersonActions>() {

    @GetMapping("/{ID}")
    fun getById(@PathVariable("ID") personId:Int) : ApiResponse<PersonDTO, String> {
        return executor.getPerson(personId)
    }

    @GetMapping("")
    fun getAllPeople(@RequestParam(required = false) page: Int?,
                     @RequestParam(required = false) size: Int?) : ApiResponse<List<PersonDTO>, String> {
        return executor.getAllPeople(page?: -1 ,size ?: -1)
    }

    @GetMapping("/{ID}/productions")
    fun getProductionAndRoleByPersonId(@PathVariable("ID") personId: Int,
                               @RequestParam(required = false) page: Int?,
                               @RequestParam(required = false) size: Int?,
                               response: HttpServletResponse): ApiResponse<List<ProductionRoleDTO>,String>{
        return executor.getProductionAndRoleByPersonId(personId,page?: -1 ,size ?: -1,response)
    }


    //query: field[:~]value,field2[:~]value2 etc
    //ex q=fullName~μαρια κ,id:1928
    //will search for person where has μαρια in the name AND id=1928
    @GetMapping("/search")
    fun searchPeople(@RequestParam("q") query: String,
                     @RequestParam(required = false) page: Int?,
                     @RequestParam(required = false) size: Int?,
                     response: HttpServletResponse): ApiResponse<List<PersonDTO>, String> {
        return executor.searchPeople(query, page?: -1, size ?: -1, response)
    }
}