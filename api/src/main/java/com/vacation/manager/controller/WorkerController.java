package com.vacation.manager.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vacation.manager.model.WorkerExtraDays;
import com.vacation.manager.model.api.WorkerExtraDaysApi;
import com.vacation.manager.model.api.form.RegisterEmployeeForm;
import com.vacation.manager.model.api.WorkerApi;
import com.vacation.manager.service.WorkersService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/workers")
public class WorkerController {

    private final WorkersService workersService;
    private final ModelMapper modelMapper;

    public WorkerController(WorkersService workersService, ModelMapper modelMapper) {
        this.workersService = workersService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{mail}/{enterpriseName}")
    public ResponseEntity<WorkerApi> getWorkerByEmailAndEnterprise(
            @PathVariable String mail, @PathVariable String enterpriseName) {

        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.getWorkerByEmailAndEnterprise(mail, enterpriseName), WorkerApi.class));
    }

    @PutMapping(value = "/{mail}/{enterpriseName}")
    public ResponseEntity<WorkerApi> setWorker(
            @PathVariable String mail, @PathVariable String enterpriseName,
            @Valid @RequestBody WorkerApi workerApi) {

        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.setWorker(mail, enterpriseName, workerApi), WorkerApi.class));
    }

    @GetMapping(value = "/HR/{enterpriseName}")
    public ResponseEntity<List<WorkerApi>> getEmployeesInCompany(@PathVariable String enterpriseName) {
        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.getByEnterpriseName(enterpriseName),
                        new TypeReference<List<WorkerApi>>() {}.getType()));
    }

    @PostMapping(value = "/CEO/add")
    public ResponseEntity<WorkerApi> createEmployee(@Valid @RequestBody RegisterEmployeeForm registerEmployeeForm) {
        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.createEmployee(registerEmployeeForm), WorkerApi.class));
    }

    @PutMapping(value = "/confirm/{mail}/{enterpriseName}")
    public ResponseEntity<WorkerApi> confirmEmployee(@PathVariable String mail, @PathVariable String enterpriseName) {
        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.confirmWorker(mail, enterpriseName), WorkerApi.class));
    }

    @GetMapping(value = "/employee/{varsId}")
    public ResponseEntity<WorkerExtraDaysApi> getWorkerVars(@PathVariable Long varsId) {
        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.getWorkerDateVars(varsId), WorkerExtraDaysApi.class));
    }

    @PutMapping(value = "/employee/{email}/{enterpriseName}")
    public ResponseEntity<WorkerExtraDaysApi> setWorkerVars(
            @PathVariable String email,
            @Valid @RequestBody WorkerExtraDaysApi workerExtraDaysApi, @PathVariable String enterpriseName) {

        return ResponseEntity.ok()
                .body(modelMapper.map(
                        workersService.setWorkerDataVars(email, workerExtraDaysApi, enterpriseName), WorkerExtraDaysApi.class));
    }
}
