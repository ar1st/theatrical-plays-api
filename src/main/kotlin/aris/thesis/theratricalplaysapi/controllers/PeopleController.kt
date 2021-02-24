package aris.thesis.theratricalplaysapi.controllers

import aris.thesis.theratricalplaysapi.entities.Person
import aris.thesis.theratricalplaysapi.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/people")
class PeopleController {
    @Autowired
    lateinit var personRepository: PersonRepository

    @GetMapping("")
    fun getAllAuthors(): MutableList<Person?> {
        return personRepository.findAll()
    }
}