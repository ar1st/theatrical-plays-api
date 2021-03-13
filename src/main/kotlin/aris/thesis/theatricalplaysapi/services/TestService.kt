package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.repositories.PersonRepository
import aris.thesis.theatricalplaysapi.specifications.PersonSpecification
import aris.thesis.theatricalplaysapi.specifications.SearchCriteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*
import javax.annotation.PostConstruct

@Service
@Suppress("unused")
class TestService {
    @Autowired
    lateinit var personRepository: PersonRepository


    @PostConstruct
    fun test() {
//        val date = GregorianCalendar(2019, 12,1).time
//        val personSpecification = PersonSpecification(SearchCriteria("fullName", ":", "Μαρια "))
//        val dateSpec = PersonSpecification(SearchCriteria("timestamp", ">",date))
//
//        val users = personRepository.findAll(Specification.where(personSpecification).and(dateSpec))

    }
}