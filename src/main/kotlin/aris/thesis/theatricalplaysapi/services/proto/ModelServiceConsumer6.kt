package aris.thesis.theatricalplaysapi.services.proto

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
abstract class ModelServiceConsumer6<S1: ModelService, S2: ModelService, S3: ModelService, S4: ModelService, S5: ModelService, S6: ModelService> {

    @Autowired
    lateinit var firstService: S1

    @Autowired
    lateinit var secondService: S2

    @Autowired
    lateinit var thirdService: S3

    @Autowired
    lateinit var fourthService: S4

    @Autowired
    lateinit var fifthService: S5

    @Autowired
    lateinit var sixthService: S6
}