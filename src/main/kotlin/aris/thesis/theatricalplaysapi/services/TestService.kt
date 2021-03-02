package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.repositories.PersonRepository
import aris.thesis.theatricalplaysapi.responses.PersonAndRoleResponse
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
        val rawData = personRepository.findRoleByProduction(234)

        val mapper = jacksonObjectMapper()  // keep around and re-use

        val data: List<PersonAndRoleResponse> = mapper.convertValue(rawData)

        println()
    }
}