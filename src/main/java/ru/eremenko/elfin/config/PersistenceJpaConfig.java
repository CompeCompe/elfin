package ru.eremenko.elfin.config;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * @author eremenko
 */
@Configuration
@EnableElasticsearchRepositories
@EnableJpaRepositories(basePackages = "ru.eremenko.elfin.repository")
public class PersistenceJpaConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Primary
    public HikariDataSource dataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();

        dataSource.setPoolName(properties.getName());

        return dataSource;
    }

    @Bean
    public SpringLiquibase liquibasePG(HikariDataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:changelogs/db-changelog.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration
                = ClientConfiguration.builder()
                .connectedTo("elasticsearch:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }
}
