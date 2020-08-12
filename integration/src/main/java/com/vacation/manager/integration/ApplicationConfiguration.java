package com.vacation.manager.integration;

import com.vacation.manager.repository.EnterpriseRepository;
import com.vacation.manager.repository.LeaveRepository;
import com.vacation.manager.repository.WorkerRepository;
import org.jooq.DSLContext;
import org.jooq.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public DataSourceConnectionProvider connectionProvider(DataSource dataSource) {
        return new DataSourceConnectionProvider
                (new TransactionAwareDataSourceProxy(dataSource));
    }

    @Bean
    public DSLContext dsl(DefaultConfiguration configuration) {
        return new DefaultDSLContext(configuration);
    }

    @Bean
    public LeaveRepository leaveRepository(DSLContext dsl) {
        return new LeaveRepository(dsl);
    }

    @Bean
    public WorkerRepository workerRepository(DSLContext dsl) {
        return new WorkerRepository(dsl);
    }

    @Bean
    public EnterpriseRepository enterpriseRepository(DSLContext dsl) {
        return new EnterpriseRepository(dsl);
    }
}
