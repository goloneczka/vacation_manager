package com.vacation.manager.service;

import com.vacation.manager.exception.AppExceptionBuilder;
import com.vacation.manager.messages.RolesMessages;
import com.vacation.manager.messages.WorkersMessages;
import com.vacation.manager.model.RoleWorker;
import com.vacation.manager.model.Worker;
import com.vacation.manager.repository.WorkerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class WorkersService {

    private final WorkerRepository workerRepository;
    private final PasswordEncoder passwordEncoder;

    public WorkersService(WorkerRepository workerRepository, PasswordEncoder passwordEncoder) {
        this.workerRepository = workerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Worker createWorker(Worker worker) {
        worker.setPassword(passwordEncoder.encode(worker.getPassword()));
        return workerRepository.createWorker(worker)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.CREATE_FAILURE).build());
    }

    public List<RoleWorker> createRoleToWorker(Integer workerId, List<Integer> rolesId) {
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
}
