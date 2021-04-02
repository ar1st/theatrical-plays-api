package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.controllers.actions.def.PersonActions
import aris.thesis.theatricalplaysapi.controllers.base.TheatricalPlaysRestController
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionRoleDTO
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/people")
class PersonController: TheatricalPlaysRestController<PersonActions>() {

    @GetMapping("/{ID}")
    fun getById(@PathVariable("ID") personId:Int) : ApiResponse<PersonDTO, String> {
        return executor.getPerson(personId)
    }

    //page minValue = 0, size minValue=1
    //values less than the aforementioned will return the all the elements
    @GetMapping("")
    fun getAllPeople(@RequestParam(required = false) page: Int?,
                     @RequestParam(required = false) size: Int?) : ApiResponse<Page<PersonDTO>, String> {
        return executor.getAllPeople(page?: -1 ,size?: -1)
    }

    @GetMapping("/role")
    fun getPeopleByRole(@RequestParam(required = true) value: String,
                     @RequestParam(required = false) page: Int?,
                     @RequestParam(required = false) size: Int?): ApiResponse<Page<PersonDTO>, String> {
        return executor.getPeopleByRole(value, page?: -1, size?: -1)
    }

    @GetMapping("/{ID}/productions")
    fun getProductionAndRoleByPersonId(@PathVariable("ID") personId: Int,
                               @RequestParam(required = false) page: Int?,
                               @RequestParam(required = false) size: Int?,
                               response: HttpServletResponse): ApiResponse<Page<ProductionRoleDTO>,String>{
        return executor.getProductionAndRoleByPersonId(personId,page?: -1 ,size ?: -1,response)
    }


    //query: field[:~]value,field2[:~]value2 etc
    //ex q=fullName~μαρια κ,id:1928
    //will search for person where has μαρια in the name AND id=1928
    @GetMapping("/search")
    fun searchPeople(@RequestParam("q") query: String,
                     @RequestParam(required = false) page: Int?,
                     @RequestParam(required = false) size: Int?,
                     response: HttpServletResponse): ApiResponse<Page<PersonDTO>, String> {
        return executor.searchPeople(query, page?: -1, size ?: -1, response)
    }
}