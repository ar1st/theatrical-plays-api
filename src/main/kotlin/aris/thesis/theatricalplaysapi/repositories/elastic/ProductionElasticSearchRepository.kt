package aris.thesis.theatricalplaysapi.repositories.elastic

import aris.thesis.theatricalplaysapi.entities.Production
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface ProductionElasticSearchRepository: ElasticsearchRepository<Production, Int> {

    fun findByDescriptionContainingIgnoreCaseOrTitleContainingIgnoreCase(description: String, title: String): List<Production>
}