package com.vacation.manager.controller;

import com.vacation.manager.exception.AppException;
import com.vacation.manager.model.Enterprise;
import com.vacation.manager.model.Worker;
import com.vacation.manager.model.api.EnterpriseApi;
import com.vacation.manager.model.api.form.RegisterCompanyForm;
import com.vacation.manager.model.api.WorkerApi;
import com.vacation.manager.service.EnterpriseService;
import com.vacation.manager.service.WorkersService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;
    private final WorkersService workersService;
    private final ModelMapper modelMapper;


    public EnterpriseController(EnterpriseService enterpriseService, WorkersService workersService,
                                ModelMapper modelMapper) {
        this.enterpriseService = enterpriseService;
        this.workersService = workersService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/enterprise")
    @Transactional(rollbackFor = AppException.class)
    public ResponseEntity<WorkerApi> createEnterprise(@Valid @RequestBody RegisterCompanyForm registerCompanyForm) {
        Enterprise enterprise = enterpriseService.createEnterprise(modelMapper.map(registerCompanyForm, Enterprise.class));
        Worker worker = workersService.addCeo(registerCompanyForm, enterprise.getId());
        return ResponseEntity.ok().body(modelMapper.map(worker, WorkerApi.class));
    }

    @PutMapping(value = "/confirm/{mail}/{enterpriseId}")
    @Transactional(rollbackFor = AppException.class)
    public ResponseEntity<WorkerApi> confirmEnterpriseAndCeo(@PathVariable String mail, @PathVariable Long enterpriseId) {
        enterpriseService.confirmEnterprise(enterpriseId);
        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.confirmWorker(mail, enterpriseId), WorkerApi.class));
    }

    @GetMapping(value = "/enterprise/{Id}")
    public ResponseEntity<EnterpriseApi> getEnterpriseById(@PathVariable Long Id) {
        return ResponseEntity.ok()
                .body(modelMapper.map(enterpriseService.getEnterpriseById(Id), EnterpriseApi.class));

    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<EnterpriseApi> getEnterpriseByName(@PathVariable String name) {
        return ResponseEntity.ok()
                .body(modelMapper.map(enterpriseService.getEnterpriseByName(name), EnterpriseApi.class));

    }



}
