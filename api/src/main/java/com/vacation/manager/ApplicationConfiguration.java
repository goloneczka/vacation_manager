package com.vacation.manager;


import com.vacation.manager.model.Enterprise;
import com.vacation.manager.model.PaidLeave;
import com.vacation.manager.model.Worker;
import com.vacation.manager.model.api.EnterpriseApi;
import com.vacation.manager.model.api.PaidLeaveApi;
import com.vacation.manager.model.api.WorkerApi;
import com.vacation.manager.model.api.form.RegisterCompanyForm;
import com.vacation.manager.model.api.form.RegisterEmployeeForm;
import com.vacation.manager.repository.EnterpriseRepository;
import com.vacation.manager.repository.LeaveRepository;
import com.vacation.manager.repository.WorkerRepository;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


import javax.sql.DataSource;
import java.util.Properties;

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

        mapper.typeMap(WorkerApi.class, Worker.class).addMappings(mapping -> {
            mapping.map(WorkerApi::getHired, Worker::setHiredFromString);
        });

        mapper.typeMap(Enterprise.class, EnterpriseApi.class).addMappings(mapping -> {
            mapping.map(Enterprise::getRestartTime, EnterpriseApi::setRestartFromLocalDate);
        });

        mapper.typeMap(EnterpriseApi.class, Enterprise.class).addMappings(mapping -> {
            mapping.map(EnterpriseApi::getRestartTime, Enterprise::setRestartFromString);
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
    public JavaMailSender javaMailSender ()  {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("vacationmanagerapp@gmail.com");
        mailSender.setPassword("klopek1432");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.startssl.enable", "true");
        return mailSender;
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
