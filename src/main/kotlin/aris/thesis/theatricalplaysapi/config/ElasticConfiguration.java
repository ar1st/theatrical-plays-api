package aris.thesis.theatricalplaysapi.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "aris.thesis.theatricalplaysapi.repositories.elastic")
@ComponentScan(basePackages = "aris.thesis.theatricalplaysapi")
public class ElasticConfiguration extends AbstractElasticsearchConfiguration {

    @Value("${elasticsearch.url}")
    public String elasticSearchURL;

    @NotNull
    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration config = ClientConfiguration.builder()
                                                              .connectedTo(elasticSearchURL)
                                                              .build();
        return RestClients.create(config).rest();
    }
}
