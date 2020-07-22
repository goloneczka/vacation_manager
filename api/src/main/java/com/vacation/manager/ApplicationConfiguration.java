package com.vacation.manager;


import com.vacation.manager.model.PaidLeave;
import com.vacation.manager.model.Worker;
import com.vacation.manager.model.api.PaidLeaveApi;
import com.vacation.manager.model.api.WorkerApi;
import com.vacation.manager.model.api.form.RegisterCompanyForm;
import com.vacation.manager.model.api.form.RegisterEmployeeForm;
import com.vacation.manager.repository.EnterpriseRepository;
import com.vacation.manager.repository.LeaveRepository;
import com.vacation.manager.repository.WorkerRepository;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
    public WorkerRepository jokesRepository(DSLContext dsl) {
        return new WorkerRepository(dsl);
    }

    @Bean
    public EnterpriseRepository enterpriseRepository(DSLContext dsl) {
        return new EnterpriseRepository(dsl);
    }

    @Bean
    public LeaveRepository leaveRepository(DSLContext dsl) {
        return new LeaveRepository(dsl);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        mapper.typeMap(PaidLeaveApi.class, PaidLeave.class).addMappings(mapping -> {
            mapping.map(PaidLeaveApi::getStartDate, PaidLeave::setStartDateFromString);
            mapping.map(PaidLeaveApi::getEndDate, PaidLeave::setEndDateFromString);
        });

        mapper.typeMap(PaidLeave.class, PaidLeaveApi.class).addMappings(mapping -> {
            mapping.map(PaidLeave::getStartDate, PaidLeaveApi::setStartDateFromLocalDate);
            mapping.map(PaidLeave::getEndDate, PaidLeaveApi::setEndDateFromLocalDate);
        });

        mapper.typeMap(Worker.class, WorkerApi.class).addMappings(mapping -> {
            mapping.map(Worker::getHired, WorkerApi::setHiredFromLocalDate);
        });

        mapper.typeMap(RegisterEmployeeForm.class, Worker.class).addMappings(mapping -> {
            mapping.map(RegisterEmployeeForm::getHired, Worker::setHiredFromString);
        });

        mapper.typeMap(RegisterCompanyForm.class, Worker.class).addMappings(mapping -> {
            mapping.map(RegisterCompanyForm::getHired, Worker::setHiredFromString);
        });

        return mapper;
    }

    @Bean
    public HtmlEmail htmlEmail() {
        HtmlEmail mail = new HtmlEmail();
        mail.setHostName("smtp.gmail.com");
        mail.setSmtpPort(465);
        mail.setSSLOnConnect(true);
        mail.setAuthenticator(new DefaultAuthenticator("vacationmanagerapp@gmail.com", "klopek1432"));
        return mail;
    }


    @Bean
    public DefaultConfiguration configuration(DataSourceConnectionProvider connectionProvider) {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(connectionProvider);

        jooqConfiguration
                .set(new DefaultExecuteListenerProvider(new DefaultExecuteListener()));
        jooqConfiguration.setSQLDialect(SQLDialect.POSTGRES);

        return jooqConfiguration;
    }
}
