package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.entities.Contribution
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.repositories.ContributionRepository
import aris.thesis.theatricalplaysapi.repositories.PersonRepository
import aris.thesis.theatricalplaysapi.services.types.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl: PersonService {
    //repos
    @Autowired
    lateinit var personRepository: PersonRepository
    @Autowired
    lateinit var contributionRepository: ContributionRepository

    override fun findById(personId: Int): Person? {
        return personRepository.findByIdOrNull(personId)
    }

    override fun findAllPeople(): List<Person> {
        return personRepository.findAll()
    }

    override fun findContributionsByPersonId(personId: Int): List<Contribution> {
        return contributionRepository.findByPeopleID(personId)
    }



}