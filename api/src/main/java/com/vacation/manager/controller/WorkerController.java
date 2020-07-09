package com.vacation.manager.controller;

import com.vacation.manager.model.Worker;
import com.vacation.manager.model.api.WorkerApi;
import com.vacation.manager.service.EnterpriseService;
import com.vacation.manager.service.WorkersService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/workers")
public class WorkerController {

    private final WorkersService workersService;
    private final ModelMapper modelMapper;

    public WorkerController(WorkersService workersService, ModelMapper modelMapper) {
        this.workersService = workersService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public ResponseEntity<Worker> registerWorker(Worker worker) {
        return ResponseEntity.ok().body(workersService.createWorker(worker));
    }

    @GetMapping(value = "/{mail}/{enterprise}")
    public ResponseEntity<WorkerApi> getWorkerByEmailAndEnterprise(
            @PathVariable String mail, @PathVariable String enterprise) {

        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.getWorkerByEmailAndEnterprise(mail, enterprise), WorkerApi.class));
    }
}
