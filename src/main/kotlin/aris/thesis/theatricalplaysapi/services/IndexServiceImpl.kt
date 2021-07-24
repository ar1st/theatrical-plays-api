package aris.thesis.theatricalplaysapi.services

import aris.thesis.theatricalplaysapi.constants.Indices
import aris.thesis.theatricalplaysapi.utils.ReadUtils
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.client.indices.CreateIndexRequest
import org.elasticsearch.client.indices.GetIndexRequest
import org.elasticsearch.common.xcontent.XContentType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IndexServiceImpl {

    private val indices = listOf(Indices.PRODUCTION_INDEX)

    @Autowired
    lateinit var client: RestHighLevelClient

    fun recreateIndices(deleteExisting: Boolean) {
        val settings: String = ReadUtils.loadAsString("static/es-settings.json") ?: return

        for (index in indices) {
            try {
                val indexExists: Boolean = client
                        .indices()
                        .exists(GetIndexRequest(index), RequestOptions.DEFAULT)
                if (indexExists) {
                    if (!deleteExisting) {
                        continue
                    }
                    client.indices().delete(
                        DeleteIndexRequest(index),
                        RequestOptions.DEFAULT
                    )
                }
                val createIndexRequest = CreateIndexRequest(index)
                createIndexRequest.settings(settings, XContentType.JSON)
                val mappings = loadMappings(index)
                if (mappings != null) {
                    createIndexRequest.mapping(mappings, XContentType.JSON)
                }
                client.indices().create(createIndexRequest, RequestOptions.DEFAULT)
            } catch (e: Exception) {
                println()
            }
        }
    }

    private fun loadMappings(indexName: String): String? {
        return ReadUtils.loadAsString("static/mappings/$indexName.json")
    }
}