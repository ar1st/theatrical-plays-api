package aris.thesis.theratricalplaysapi.services

import aris.thesis.theratricalplaysapi.entities.Person
import aris.thesis.theratricalplaysapi.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

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
}