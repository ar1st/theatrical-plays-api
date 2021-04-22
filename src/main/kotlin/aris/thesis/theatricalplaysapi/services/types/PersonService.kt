package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.entities.Contribution
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.services.proto.ModelService
import org.springframework.data.domain.Page
import org.springframework.data.jpa.domain.Specification

interface PersonService: ModelService {
    fun getById(personId: Int): Person?

    fun getAllPeople(page: Int, size: Int): Page<Person>

    fun getPeopleByRole(value: String, page: Int, size: Int): Page<Person>

    fun getPeopleBySpec(spec: Specification<Person>, page: Int, size: Int): Page<Person>

    fun getContributionsByPersonId(personId: Int, page: Int, size: Int): Page<Contribution>

    fun getByContributionId(contributionId: Int): Person?

}