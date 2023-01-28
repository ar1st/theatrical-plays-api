package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.curators.DataCurator
import aris.thesis.theatricalplaysapi.dtos.request.CreatePersonRequest
import aris.thesis.theatricalplaysapi.dtos.response.EntityId
import aris.thesis.theatricalplaysapi.entities.Contribution
import aris.thesis.theatricalplaysapi.entities.Image
import aris.thesis.theatricalplaysapi.entities.Person
import aris.thesis.theatricalplaysapi.repositories.ContributionRepository
import aris.thesis.theatricalplaysapi.repositories.ImageRepository
import aris.thesis.theatricalplaysapi.repositories.PersonRepository
import aris.thesis.theatricalplaysapi.services.types.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl : PersonService {

    @Autowired
    lateinit var personRepository: PersonRepository

    @Autowired
    lateinit var imageRepository: ImageRepository

    @Autowired
    lateinit var contributionRepository: ContributionRepository



    override fun getById(personId: Int): Person? {
        return personRepository.findByIdOrNull(personId)
    }

    override fun getAllPeople(page: Int, size: Int): Page<Person> {
        return if (page >= 0 && size > 0) {
            personRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "fullName")))
        } else {
            personRepository.findAll(PageRequest.of(0, Int.MAX_VALUE, Sort.by(Sort.Direction.ASC, "fullName")))
        }
    }

    override fun getPeopleByRole(value: String, page: Int, size: Int): Page<Person> {
        return if (page >= 0 && size > 0) {
            personRepository.findPeopleByRole(value, PageRequest.of(page, size))
        } else {
            personRepository.findPeopleByRole(value, Pageable.unpaged())
        }
    }

    override fun getPeopleByLetter(value: String, page: Int, size: Int): Page<Person> {
        return if (page >= 0 && size > 0) {
            personRepository.findPeopleByLetter(value, PageRequest.of(page, size))
        } else {
            personRepository.findPeopleByLetter(value, Pageable.unpaged())
        }
    }

    override fun getPeopleBySpec(spec: Specification<Person>, page: Int, size: Int): Page<Person> {
        return if (page >= 0 && size > 0) {
            personRepository.findAll(spec, PageRequest.of(page, size))
        } else {
            personRepository.findAll(spec, Pageable.unpaged())
        }
    }

    override fun getContributionsByPersonId(personId: Int, page: Int, size: Int): Page<Contribution> {
        return if (page >= 0 && size > 0) {
            contributionRepository.findByPeopleID(personId, PageRequest.of(page,size))
        } else {
            contributionRepository.findByPeopleID(personId, Pageable.unpaged() )

        }
    }

    override fun getByContributionId(contributionId: Int): Person? {
        return personRepository.findByContributionId(contributionId)
    }

    override fun getPhotosByPersonId(personId: Int): Set<Image> {
        return imageRepository.findByPersonId(personId)
    }

    override fun createPerson(request: CreatePersonRequest): EntityId {
        val personByFullName = personRepository.findByFullName(request.fullName)
        val id = EntityId()

        if (personByFullName == null) {
            DataCurator.curateFullName(request.fullName)
            val person = personRepository.save(Person(request.fullName))
            id.newId = person.id
        } else {
            id.existingId = personByFullName.id
        }

        return id
    }
}