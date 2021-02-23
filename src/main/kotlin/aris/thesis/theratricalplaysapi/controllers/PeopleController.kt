package aris.thesis.theratricalplaysapi.controllers

import aris.thesis.theratricalplaysapi.entities.Persons
import aris.thesis.theratricalplaysapi.repositories.PersonsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/people")
class PeopleController {
    @Autowired
    lateinit var personsRepository: PersonsRepository

    @GetMapping("")
    fun getAllAuthors(): List<Persons> {
        return personsRepository.findAll()
    }
}