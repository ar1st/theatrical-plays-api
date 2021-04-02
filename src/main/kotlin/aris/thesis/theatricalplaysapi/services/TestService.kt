package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.repositories.ImageRepository
import aris.thesis.theatricalplaysapi.repositories.PersonRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
@Suppress("unused")
class TestService {
    @Autowired
    lateinit var personRepository: PersonRepository
    @Autowired
    lateinit var imageRepository: ImageRepository

    @Autowired
    lateinit var imageServiceImpl: ImageServiceImpl

    @PostConstruct
    fun test() {
        val r1 = imageServiceImpl.getById(200330)
        println()
    }
}