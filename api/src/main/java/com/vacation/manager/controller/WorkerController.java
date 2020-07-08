package com.vacation.manager.controller;

import com.vacation.manager.model.Worker;
import com.vacation.manager.service.EnterpriseService;
import com.vacation.manager.service.WorkersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    WorkersService workersService;

    public WorkerController(EnterpriseService enterpriseService, WorkersService workersService) {
        this.workersService = workersService;
    }

    @PostMapping()
    public ResponseEntity<Worker> registerWorker(Worker worker) {
        return ResponseEntity.ok().body(workersService.createWorker(worker));
    }

    @GetMapping(value = "/{mail}/{enterprise}")
    public ResponseEntity<Worker> getWorkerByEmailAndEnterprise(
            @PathVariable String mail, @PathVariable String enterprise) {
        return ResponseEntity.ok().body(workersService.getWorkerByEmailAndEnterprise(mail, enterprise));
    }
}
