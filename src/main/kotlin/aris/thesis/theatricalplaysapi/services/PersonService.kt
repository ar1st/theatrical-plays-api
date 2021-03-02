package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull

@Service
class PersonService {
    @Autowired
    lateinit var personRepository: PersonRepository

    fun findPaginated(pageNo: Int, pageSize: Int): List<Person> {
        val paging: Pageable = PageRequest.of(pageNo, pageSize)
        val pagedResult: Page<Person> = personRepository.findAll(paging)
        val totalPage = pagedResult.totalPages
        val totalElements = pagedResult.totalElements

        return pagedResult.toList()
    }

    fun findAll(): List<Person> {
        return personRepository.findAll()
    }

    fun findById(personId: Int): Person? {
        return personRepository.findByIdOrNull(personId)
    }

    fun findByProduction(productionId: Int): List<Map<String, String>> {
        return personRepository.findRoleByProduction(productionId)
    }
}