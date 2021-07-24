package aris.thesis.theatricalplaysapi.config.loaders

import aris.thesis.theatricalplaysapi.repositories.elastic.ProductionElasticSearchRepository
import aris.thesis.theatricalplaysapi.repositories.ProductionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class ElasticSearchLoader {

    @Autowired
    lateinit var productionRepository: ProductionRepository

    @Autowired
    lateinit var productionElasticSearchRepository: ProductionElasticSearchRepository

    @PostConstruct
    fun loadProductions() {
        val productions = productionRepository.findAll()

        productionElasticSearchRepository.saveAll(productions)
    }
}