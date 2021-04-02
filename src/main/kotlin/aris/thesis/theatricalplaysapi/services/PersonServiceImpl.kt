package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.entities.Contribution
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.repositories.ContributionRepository
import aris.thesis.theatricalplaysapi.repositories.PersonRepository
import aris.thesis.theatricalplaysapi.services.types.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl: PersonService {
    //repos
    @Autowired
    lateinit var personRepository: PersonRepository
    @Autowired
    lateinit var contributionRepository: ContributionRepository

    override fun getById(personId: Int): Person? {
        return personRepository.findByIdOrNull(personId)
    }

    override fun getAllPeople(): List<Person> {
        return personRepository.findAll()
    }

    override fun getAllPeople(pageable: Pageable): Page<Person> {
        return personRepository.findAll(pageable)
    }

    override fun getPeopleBySpec(spec: Specification<Person>): List<Person> {
        return personRepository.findAll(spec)
    }

    override fun getPeopleBySpec(spec: Specification<Person>, pageable: Pageable): Page<Person> {
        return personRepository.findAll(spec,pageable)
    }

    override fun getContributionsByPersonId(personId: Int): List<Contribution> {
        return contributionRepository.findByPeopleID(personId)
    }

    override fun getContributionsByPersonId(personId: Int, pageable: Pageable): Page<Contribution> {
        return contributionRepository.findByPeopleID(personId,pageable)
    }

    override fun getByContributionId(contributionId: Int): Person? {
        return personRepository.findByContributionId(contributionId)
    }


}