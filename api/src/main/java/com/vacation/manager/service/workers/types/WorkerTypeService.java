package com.vacation.manager.service.workers.types;

import com.vacation.manager.exception.AppExceptionBuilder;
import com.vacation.manager.messages.RolesMessages;
import com.vacation.manager.messages.WorkersMessages;
import com.vacation.manager.model.RoleWorker;
import com.vacation.manager.model.Worker;
import com.vacation.manager.repository.WorkerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Component
abstract class WorkerTypeService {

    private final WorkerRepository workerRepository;
    private final PasswordEncoder passwordEncoder;

    protected WorkerTypeService(WorkerRepository workerRepository, PasswordEncoder passwordEncoder) {
        this.workerRepository = workerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    protected Worker createWorker(Worker worker) {
        worker.setPassword(passwordEncoder.encode(worker.getPassword()));
        worker.setEmployeeVarsId(workerRepository.createWorkerVars(Period.between(worker.getHired(), LocalDate.now()).getYears())
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.CREATE_FAILURE_VARS).build()).getId());
        return workerRepository.createWorker(worker)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.CREATE_FAILURE_DUP_KEYS).build());
    }

    protected List<RoleWorker> createRoleToWorker(Integer workerId, List<Integer> rolesId) {
        return rolesId.stream().map( id -> workerRepository.createRoleToWorker(workerId, id)
                .orElseThrow(() -> new AppExceptionBuilder().addError(RolesMessages.CREATE_FAILURE).build()))
                .collect(Collectors.toList());
    }
}
