package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.entities.Contribution
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.services.proto.ModelService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface PersonService: ModelService {
    fun getById(personId: Int): Person?

    fun getAllPeople(): List<Person>

    fun getAllPeople(pageable: Pageable): Page<Person>

    fun getPeopleBySpec(spec: Specification<Person>): List<Person>

    fun getPeopleBySpec(spec: Specification<Person>, pageable: Pageable): Page<Person>

    fun getContributionsByPersonId(personId: Int): List<Contribution>

    fun getContributionsByPersonId(personId: Int, pageable: Pageable): Page<Contribution>

    fun getByContributionId(contributionId: Int): Person?

}