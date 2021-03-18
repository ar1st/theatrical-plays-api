package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.repositories.PersonRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
@Suppress("unused")
class TestService {
    @Autowired
    lateinit var personRepository: PersonRepository


    @PostConstruct
    fun test() {
//        val date = GregorianCalendar(2019, 12,1).time
//        val personSpecification = PersonSpecification(SearchCriteria("fullName", SearchOperation.getSimpleOperation(':'), "Μαρια "))
//    //    val dateSpec = PersonSpecification(SearchCriteria("timestamp", SearchOperation.getSimpleOperation(':') ,date))
//        val builder = PersonSpecificationBuilder()
//
//        val users = personRepository.findAll(personSpecification)
//
//        println()
    }
}