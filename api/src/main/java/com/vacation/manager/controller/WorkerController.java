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

    @GetMapping(value = "/{mail}/{enterprise}")
    public ResponseEntity<WorkerApi> getWorkerByEmailAndEnterprise(
            @PathVariable String mail, @PathVariable String enterprise) {

        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.getWorkerByEmailAndEnterprise(mail, enterprise), WorkerApi.class));
    }

    @PutMapping(value = "/{mail}/{enterprise}")
    public ResponseEntity<WorkerApi> setWorker(
            @PathVariable String mail, @PathVariable String enterprise,
            @Valid @RequestBody WorkerApi workerApi) {

        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.setWorker(mail, enterprise, workerApi), WorkerApi.class));
    }

    @GetMapping(value = "/HR/{enterpriseId}")
    public ResponseEntity<List<WorkerApi>> getEmployeesInCompany(@PathVariable Long enterpriseId) {
        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.getEmployeesInCompany(enterpriseId),
                        new TypeReference<List<WorkerApi>>() {}.getType()));
    }

    @PostMapping(value = "/CEO/add")
    public ResponseEntity<WorkerApi> addEmployee(@Valid @RequestBody RegisterEmployeeForm registerEmployeeForm) {
        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.addEmployee(registerEmployeeForm), WorkerApi.class));
    }

    @PutMapping(value = "/confirm/{mail}/{enterpriseId}")
    public ResponseEntity<WorkerApi> confirmEnterpriseAndCeo(@PathVariable String mail, @PathVariable Long enterpriseId) {
        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.confirmWorker(mail, enterpriseId), WorkerApi.class));
    }

    @GetMapping(value = "/employee/{varsId}")
    public ResponseEntity<WorkerExtraDaysApi> getWorkerVars(@PathVariable Long varsId) {
        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.getWorkerDateVars(varsId), WorkerExtraDaysApi.class));
    }

    @PutMapping(value = "/employee/{varsId}")
    public ResponseEntity<WorkerExtraDaysApi> setWorkerVars(
            @PathVariable Long varsId,
            @Valid @RequestBody WorkerExtraDaysApi workerExtraDaysApi) {

        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.setWorkerDataVars(varsId, workerExtraDaysApi), WorkerExtraDaysApi.class));
    }
}
