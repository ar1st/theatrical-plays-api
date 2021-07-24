package aris.thesis.theatricalplaysapi.controllers

import aris.thesis.theatricalplaysapi.constants.RestPathConstants
import aris.thesis.theatricalplaysapi.services.IndexServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/index",
                produces = [MediaType.APPLICATION_JSON_VALUE + RestPathConstants.MEDIA_TYPE_UTF_8])
class IndexController {

    @Autowired
    lateinit var indexService: IndexServiceImpl

    @GetMapping("/recreate")
    fun recreateAllIndices() {
        indexService.recreateIndices(true)
    }
}