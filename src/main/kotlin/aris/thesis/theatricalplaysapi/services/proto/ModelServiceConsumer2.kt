package aris.thesis.theatricalplaysapi.services.proto

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
abstract class ModelServiceConsumer2<S1: ModelService, S2: ModelService> {

    @Autowired
    lateinit var firstService: S1

    @Autowired
    lateinit var secondService: S2
}