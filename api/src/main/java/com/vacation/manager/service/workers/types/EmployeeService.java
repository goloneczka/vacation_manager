package com.vacation.manager.service.workers.types;

import com.vacation.manager.model.Worker;
import com.vacation.manager.repository.WorkerRepository;
import com.vacation.manager.service.workers.WorkerType;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Arrays;
import java.util.Collections;

import static com.vacation.manager.messages.RolesMessages.EMPLOYEE;


public class EmployeeService extends WorkerTypeService implements WorkerType {

    public EmployeeService(WorkerRepository workerRepository, PasswordEncoder passwordEncoder) {
        super(workerRepository, passwordEncoder);
    }

    @Override
    public Worker addEmployee(Worker tmpWorker) {
        Worker worker = createWorker(tmpWorker);
        createRoleToWorker(worker.getId().intValue(), Collections.singletonList(3));
        return worker;
    }

    @Override
    public String getStrategyName() {
        return EMPLOYEE;
    }
}
