package aris.thesis.theatricalplaysapi.services.types

import aris.thesis.theatricalplaysapi.entities.Contribution
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.services.proto.ModelService

interface PersonService: ModelService {
    fun findById(personId: Int): Person?

    fun findAllPeople(): List<Person>

    fun findContributionsByPersonId(personId: Int) : List<Contribution>
}