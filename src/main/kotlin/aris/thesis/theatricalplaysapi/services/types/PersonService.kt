package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.entities.Contribution
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.services.proto.ModelService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface PersonService: ModelService {
    fun findById(personId: Int): Person?

    fun findAllPeople(): List<Person>

    fun findAllPeople(pageable: Pageable): Page<Person>

    fun findPeopleBySpec(spec: Specification<Person>): List<Person>

    fun findPeopleBySpec(spec: Specification<Person>,pageable: Pageable): Page<Person>

    fun findContributionsByPersonId(personId: Int) : List<Contribution>

    fun findContributionsByPersonId(personId: Int, pageable: Pageable) : Page<Contribution>
}