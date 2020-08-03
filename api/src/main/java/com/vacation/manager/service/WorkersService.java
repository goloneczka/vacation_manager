package com.vacation.manager.service;

import com.vacation.manager.exception.AppException;
import com.vacation.manager.exception.AppExceptionBuilder;
import com.vacation.manager.messages.RolesMessages;
import com.vacation.manager.messages.WorkersMessages;
import com.vacation.manager.model.RoleWorker;
import com.vacation.manager.model.Worker;
import com.vacation.manager.model.WorkerExtraDays;
import com.vacation.manager.model.api.WorkerApi;
import com.vacation.manager.model.api.WorkerExtraDaysApi;
import com.vacation.manager.model.api.form.RegisterCompanyForm;
import com.vacation.manager.model.api.form.RegisterEmployeeForm;
import com.vacation.manager.repository.WorkerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class WorkersService {

    private final WorkerRepository workerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final EmailService emailService;

    public WorkersService(WorkerRepository workerRepository, PasswordEncoder passwordEncoder,
                          ModelMapper modelMapper, EmailService emailService) {
        this.workerRepository = workerRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
    }


    private Worker createWorker(Worker worker) {
        worker.setPassword(passwordEncoder.encode(worker.getPassword()));
        worker.setEmployeeVarsId(workerRepository.createWorkerVars(Period.between(worker.getHired(), LocalDate.now()).getYears())
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.CREATE_FAILURE_VARS).build()).getId());
        return workerRepository.createWorker(worker)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.CREATE_FAILURE_DUP_KEYS).build());
    }

    @Transactional(rollbackFor = AppException.class)
    public Worker addCeo(RegisterCompanyForm registerCompanyForm, Long enterpriseId){
        Worker tmpWorker = modelMapper.map(registerCompanyForm, Worker.class);
        tmpWorker.setEnterpriseId(enterpriseId);
        Worker worker = createWorker(tmpWorker);
        createRoleToWorker(worker.getId().intValue(), Arrays.asList(1, 2, 3));
        emailService.sendEmailMessageToNewCeo(worker.getEmail(), worker.getEnterpriseId().intValue());
        return worker;
    }

    private List<RoleWorker> createRoleToWorker(Integer workerId, List<Integer> rolesId) {
        return rolesId.stream().map( id -> workerRepository.createRoleToWorker(workerId, id)
                .orElseThrow(() -> new AppExceptionBuilder().addError(RolesMessages.CREATE_FAILURE).build()))
                .collect(Collectors.toList());
    }

    public Worker getWorkerByEmailAndEnterprise(String email, String enterprise){
        Worker worker = workerRepository.findConfirmedByEmailAndEnterprise(email, enterprise)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.NOT_FOUND).build());
        worker.setRoles(workerRepository.getUserRoles(worker.getId()));
        return worker;
    }

    public Worker confirmWorker(String mail, Long enterpriseId) {
        return workerRepository.confirmWorker(mail, enterpriseId)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.CREATE_CONFIRM_FAILURE).build());
    }

    public List<Worker> getEmployeesInCompany(Long enterpriseId) {
        return workerRepository.getEmployeesByEnterpriseId(enterpriseId);
    }

    @Transactional(rollbackFor = AppException.class)
    public Worker addEmployee(RegisterEmployeeForm registerEmployeeForm) {
        Worker tmpWorker = modelMapper.map(registerEmployeeForm, Worker.class);
        String passwd = alphaNumericString();
        tmpWorker.setPassword(passwd);
        Worker worker = createWorker(tmpWorker);
        if (registerEmployeeForm.getIsHR())
            createRoleToWorker(worker.getId().intValue(), Arrays.asList(2, 3));
         else
            createRoleToWorker(worker.getId().intValue(), Collections.singletonList(3));
        emailService.sendEmailToNewEmployee(worker.getEmail(), worker.getEnterpriseId().intValue(), passwd);
        return worker;
    }

    private String alphaNumericString() {
        String AB = "1023456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public WorkerExtraDays getWorkerDateVars(Long varsId) {
        return workerRepository.getWorkerExtraDaysById(varsId)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.NOT_FOUND_VARS).build());
    }

    public WorkerExtraDays setWorkerDateVars(Long varsId, WorkerExtraDaysApi workerExtraDaysApi) {
        WorkerExtraDays tmpWorkerExtraDays = modelMapper.map(workerExtraDaysApi, WorkerExtraDays.class);
        return workerRepository.setWorkerExtraDaysById(varsId, tmpWorkerExtraDays)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.UPDATE_FAILURE).build());
    }

    public Worker setWorker(String mail, String enterprise, WorkerApi workerApi) {
        Worker tmpWorker = modelMapper.map(workerApi, Worker.class);
        Long id = workerRepository.findConfirmedByEmailAndEnterprise(mail, enterprise)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.NOT_FOUND).build()).getId();
        return workerRepository.setWorker(id, tmpWorker)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.UPDATE_FAILURE).build());
    }

}
