package com.vacation.manager.controller;

import com.vacation.manager.model.Worker;
import com.vacation.manager.response.ResponseStatus;
import com.vacation.manager.service.EnterpriseService;
import com.vacation.manager.service.WorkersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    EnterpriseService enterpriseService;
    WorkersService workersService;

    public WorkerController(EnterpriseService enterpriseService, WorkersService workersService) {
        this.enterpriseService = enterpriseService;
        this.workersService = workersService;
    }

    @GetMapping("/register")
    public ResponseEntity<Worker> register(Worker worker) {
        return ResponseEntity.status(ResponseStatus.OK).body(workersService.createWorker(worker));
    }
}
