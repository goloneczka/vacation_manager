package com.vacation.manager.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;

@Configuration
public class RepositoryConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource sourceDataSource() {
        return DataSourceBuilder.create().build();
    }

}
