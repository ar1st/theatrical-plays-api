package aris.thesis.theratricalplaysapi.controllers

import aris.thesis.theratricalplaysapi.entities.Person
import aris.thesis.theratricalplaysapi.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/people")
class PersonController {
    @Autowired
    lateinit var personService: PersonService

    @GetMapping
    fun findPaginatedQueryParams(@RequestParam pageNo: Int?,
                                 @RequestParam pageSize: Int?): List<Person> {
        if ( pageNo == null ||  pageSize == null) {
            return personService.findAll()
        }

        return personService.findPaginated(pageNo, pageSize)
    }

    @GetMapping("/test")
    fun testResponseEntity(httpServletResponse: HttpServletResponse): ResponseEntity<String> {
        httpServletResponse.setHeader("test","testHeader")
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body("Testing here")
    }
}