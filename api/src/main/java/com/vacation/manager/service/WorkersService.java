package com.vacation.manager.service;

import com.vacation.manager.exception.AppExceptionBuilder;
import com.vacation.manager.exception.messages.RolesMessages;
import com.vacation.manager.exception.messages.WorkersMessages;
import com.vacation.manager.model.Role;
import com.vacation.manager.model.RoleWorker;
import com.vacation.manager.model.Worker;
import com.vacation.manager.repositoryP.WorkerJooqRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class WorkersService {

    private final WorkerJooqRepository workerRepository;

    public WorkersService(WorkerJooqRepository workerRepository) {
        this.workerRepository = workerRepository;
    }


    public Worker createWorker(Worker worker) {
        return workerRepository.createWorker(worker)
                .orElseThrow(() -> new AppExceptionBuilder().addError(WorkersMessages.CREATE_FAILURE).build());
    }

    public List<RoleWorker> createRoleToWorker(Integer workerId, List<Integer> rolesId) {
        return rolesId.stream().map( id -> workerRepository.createRoleToWorker(workerId, id)
                .orElseThrow(() -> new AppExceptionBuilder().addError(RolesMessages.CREATE_FAILURE).build()))
                .collect(Collectors.toList());
    }
}
