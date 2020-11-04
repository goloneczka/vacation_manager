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
import com.vacation.manager.service.workers.WorkerContext;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static com.vacation.manager.messages.RolesMessages.*;


@Service
public class WorkersService {

    private final WorkerRepository workerRepository;
    private final ModelMapper modelMapper;
    private final EmailService emailService;
    private final EnterpriseService enterpriseService;
    private final LeaveService leaveService;
    private final WorkerContext workerContext;

    public WorkersService(WorkerRepository workerRepository,
                          ModelMapper modelMapper, EmailService emailService, EnterpriseService enterpriseService,
                          @Lazy LeaveService leaveService, WorkerContext workerContext) {
        this.workerRepository = workerRepository;
        this.modelMapper = modelMapper;     //TODO - REPLACE TO CONTROLLER ??
        this.emailService = emailService;
        this.enterpriseService = enterpriseService;
        this.leaveService = leaveService;
        this.workerContext = workerContext;
    }


    @Transactional(rollbackFor = AppException.class)
    public Worker createCeo(RegisterCompanyForm registerCompanyForm) {
        Worker tmpWorker = modelMapper.map(registerCompanyForm, Worker.class);
        Worker worker = workerContext
                .findStrategy(CEO)
                .addEmployee(tmpWorker);
        emailService.sendEmailMessageToNewCeo(worker.getEmail(), worker.getEnterpriseName());
        return worker;
    }

    @Transactional(rollbackFor = AppException.class)
    public Worker createEmployee(RegisterEmployeeForm registerEmployeeForm) {
        Worker tmpWorker = modelMapper.map(registerEmployeeForm, Worker.class);
        String passwd = alphaNumericString();
        tmpWorker.setPassword(passwd);
        Worker worker;
        if (registerEmployeeForm.getIsHR())
            worker = workerContext
                    .findStrategy(HR)
                    .addEmployee(tmpWorker);
        else
            worker = workerContext
                    .findStrategy(EMPLOYEE)
                    .addEmployee(tmpWorker);

        emailService.sendEmailToNewEmployee(worker.getEmail(), worker.getEnterpriseName(), passwd);
        return worker;
    }


    public Worker getWorkerByEmailAndEnterprise(String email, String enterprise) {
        Worker worker = workerRepository.getConfirmedByEmailAndEnterprise(email, enterprise)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.NOT_FOUND).build());
        worker.setRoles(workerRepository.getUserRoles(worker.getId()));
        return worker;
    }

    public Worker confirmWorker(String mail, String enterpriseName) {
        return workerRepository.confirmWorker(mail, enterpriseName)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.CREATE_CONFIRM_FAILURE).build());
    }

    public List<Worker> getByEnterpriseName(String enterpriseName) {
        return workerRepository.getByEnterpriseName(enterpriseName);
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

    public WorkerExtraDays setWorkerDataVars(String email, WorkerExtraDaysApi workerExtraDaysApi, String enterprise) {
        WorkerExtraDays tmpWorkerExtraDays = modelMapper.map(workerExtraDaysApi, WorkerExtraDays.class);
        LocalDate newYear = LocalDate.of(LocalDate.now().plusYears(1).getYear(), 1, 1);
        int usedDaysPresentYear = leaveService.getWorkerLeaves(email, enterprise).stream()
                .filter(tmp -> !tmp.getStatus().equals("REJECTED") && tmp.getEndDate().isBefore(newYear))
                .mapToInt(tmp -> (int) tmp.getDays()).sum();
        if (usedDaysPresentYear > getWorkerFreeDays(tmpWorkerExtraDays, enterprise))
            throw new AppExceptionBuilder().addError(WorkersMessages.UPDATE_FAILURE_AMMOUNT_DAYS).build();
        return workerRepository.setWorkerExtraDaysById(getWorkerByEmailAndEnterprise(email, enterprise).getEmployeeVarsId(), tmpWorkerExtraDays)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.UPDATE_FAILURE).build());
    }

    public int getWorkerFreeDays(WorkerExtraDays tmpWED, String enterprise) {
        return getWorkerFutureFreeDays(tmpWED, enterprise) + tmpWED.getAnnualExtraDays()
                + tmpWED.getTransitiveDays();
    }

    public int getWorkerFutureFreeDays(WorkerExtraDays tmpWED, String enterprise) {
        return enterpriseService.getEnterpriseByName(enterprise).getFreeDays()
                + tmpWED.getExtraDays() + (tmpWED.getSeniority() >= 10 ? 6 : 0);
    }

    public Worker setWorker(String mail, String enterprise, WorkerApi workerApi) {
        Worker tmpWorker = modelMapper.map(workerApi, Worker.class);
        Long id = workerRepository.getConfirmedByEmailAndEnterprise(mail, enterprise)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.NOT_FOUND).build()).getId();
        return workerRepository.setWorker(id, tmpWorker)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.UPDATE_FAILURE).build());
    }


}
