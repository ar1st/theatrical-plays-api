package aris.thesis.theratricalplaysapi.services

import aris.thesis.theratricalplaysapi.repositories.PersonRepository
import aris.thesis.theratricalplaysapi.responses.PersonAndRoleResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import com.fasterxml.jackson.module.kotlin.*

@Service
class TestService {
    @Autowired
    lateinit var personRepository: PersonRepository

    @PostConstruct
    fun test() {
        val rawData = personRepository.findTest(234)

        val mapper = jacksonObjectMapper()  // keep around and re-use

        val data: List<PersonAndRoleResponse> = mapper.convertValue(rawData)

        println()
    }
}