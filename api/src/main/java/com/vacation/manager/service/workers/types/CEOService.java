package com.vacation.manager.service.workers.types;

import com.vacation.manager.model.Worker;
import com.vacation.manager.repository.WorkerRepository;
import com.vacation.manager.service.workers.WorkerType;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

import static com.vacation.manager.messages.RolesMessages.CEO;


public class CEOService extends WorkerTypeService implements WorkerType {

    public CEOService(WorkerRepository workerRepository, PasswordEncoder passwordEncoder) {
        super(workerRepository, passwordEncoder);
    }

    @Override
    public Worker addEmployee(Worker tmpWorker) {
        Worker worker = createWorker(tmpWorker);
        createRoleToWorker(worker.getId().intValue(), Arrays.asList(1, 2, 3));
        return worker;
    }

    @Override
    public String getStrategyName() {
        return CEO;
    }


}
