package aris.thesis.theatricalplaysapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
				ElasticsearchRepository.class})
})
public class TheatricalPlaysApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(TheatricalPlaysApiApplication.class, args);
	}
}