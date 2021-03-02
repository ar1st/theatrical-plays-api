package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.executors.ProductionExecutor
import aris.thesis.theatricalplaysapi.responses.ApiResponse
import aris.thesis.theatricalplaysapi.responses.ProductionAndRoleResponse
import aris.thesis.theatricalplaysapi.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/people")
class PersonController {
    @Autowired
    lateinit var personService: PersonService
    @Autowired
    lateinit var productionExecutor: ProductionExecutor

    @GetMapping
    fun findPaginatedQueryParams(@RequestParam pageNo: Int?,
                                 @RequestParam pageSize: Int?): List<Person> {
        if ( pageNo == null ||  pageSize == null) {
            return personService.findAll()
        }

        return personService.findPaginated(pageNo, pageSize)
    }

    @GetMapping("/{ID}/productions")
    fun findProductionByPerson(@PathVariable("ID") personId: Int,
                               response: HttpServletResponse): ApiResponse<List<ProductionAndRoleResponse>,String>{
        return productionExecutor.findByPerson(personId,response)
    }
}