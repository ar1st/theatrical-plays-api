package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.controllers.actions.def.PersonActions
import aris.thesis.theatricalplaysapi.controllers.base.TheatricalPlaysRestController
import aris.thesis.theatricalplaysapi.dtos.ApiResponse
import aris.thesis.theatricalplaysapi.dtos.PersonDTO
import aris.thesis.theatricalplaysapi.dtos.ProductionAndRoleResponse
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.executors.ProductionExecutor
import aris.thesis.theatricalplaysapi.services.PersonServiceImp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/people")
class PersonController: TheatricalPlaysRestController<PersonActions>() {
    @Autowired
    lateinit var personServiceImp: PersonServiceImp
    @Autowired
    lateinit var productionExecutor: ProductionExecutor

    @GetMapping("/{ID}")
    fun getById(@PathVariable("ID") personId:Int) : ApiResponse<PersonDTO, String> {
        return executor.getPerson(personId)
    }

    @GetMapping("")
    fun getAllPeople(@RequestParam(required = false) pageNo: Int?,
                     @RequestParam(required = false) pageSize: Int?) : ApiResponse<List<PersonDTO>, String> {
        return executor.getAllPeople()
    }



    @GetMapping("/paginated")
    fun findPaginatedQueryParams(@RequestParam pageNo: Int?,
                                 @RequestParam pageSize: Int?): List<Person> {
        if ( pageNo == null ||  pageSize == null) {

            return personServiceImp.findAllPeople()
        }

        return personServiceImp.findPaginated(pageNo, pageSize)
    }

    @GetMapping("/{ID}/productions")
    fun findProductionByPerson(@PathVariable("ID") personId: Int,
                               response: HttpServletResponse): ApiResponse<List<ProductionAndRoleResponse>,String>{
        return productionExecutor.findByPerson(personId,response)
    }
}