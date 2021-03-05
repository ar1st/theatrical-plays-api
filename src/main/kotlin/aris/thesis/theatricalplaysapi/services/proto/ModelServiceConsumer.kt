package aris.thesis.theatricalplaysapi.services.proto

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
abstract class ModelServiceConsumer<S: ModelService> {

    @Autowired
    lateinit var service: S
}