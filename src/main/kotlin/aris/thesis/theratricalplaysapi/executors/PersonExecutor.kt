package aris.thesis.theratricalplaysapi.executors

import aris.thesis.theratricalplaysapi.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PersonExecutor {
    @Autowired
    lateinit var personService: PersonService

}