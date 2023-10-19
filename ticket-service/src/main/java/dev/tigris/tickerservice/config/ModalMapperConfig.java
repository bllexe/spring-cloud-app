package dev.tigris.tickerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("dev.tigris")
@EnableElasticsearchRepositories
@ComponentScan("dev.tigris")
public class ModalMapperConfig {

    @Bean
    public ModelMapper getModalMapper(){
        return new ModelMapper();
    }
}
