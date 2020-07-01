package com.vacation.manager.service;


import com.vacation.manager.repositoryP.WorkerJooqRepository;
import org.springframework.stereotype.Service;



@Service
public class WorkersService {

    private final WorkerJooqRepository workerRepository;

    public WorkersService(WorkerJooqRepository workerRepository) {
        this.workerRepository = workerRepository;
    }



}
