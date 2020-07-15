package com.vacation.manager.controller;

import com.vacation.manager.exception.AppException;
import com.vacation.manager.model.Enterprise;
import com.vacation.manager.model.Worker;
import com.vacation.manager.model.api.RegisterForm;
import com.vacation.manager.model.api.WorkerApi;
import com.vacation.manager.service.EmailService;
import com.vacation.manager.service.EnterpriseService;
import com.vacation.manager.service.WorkersService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;
    private final WorkersService workersService;
    private final ModelMapper modelMapper;
    private final EmailService emailService;

    public EnterpriseController(EnterpriseService enterpriseService, WorkersService workersService,
                                ModelMapper modelMapper, EmailService emailService) {
        this.enterpriseService = enterpriseService;
        this.workersService = workersService;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
    }

    @PostMapping("/enterprise")
    @Transactional(rollbackFor = AppException.class)
    public ResponseEntity<WorkerApi> createEnterprise(@Valid @RequestBody RegisterForm registerForm) {
        Worker tmpWorker = modelMapper.map(registerForm, Worker.class);
        Enterprise enterprise = enterpriseService.createEnterprise(modelMapper.map(registerForm, Enterprise.class));
        tmpWorker.setEnterpriseId(enterprise.getId());
        Worker worker = workersService.createWorker(tmpWorker);
        workersService.createRoleToWorker(worker.getId().intValue(), Arrays.asList(1, 2, 3));
        emailService.sendEmailMessage(worker.getEmail(), worker.getEnterpriseId().intValue());
        return ResponseEntity.ok().body(modelMapper.map(worker, WorkerApi.class));
    }

    @PutMapping(value = "/enterprise/{mail}/{enterpriseId}")
    @Transactional(rollbackFor = AppException.class)
    public ResponseEntity<WorkerApi> confirmEnterpriseAndCeo(@PathVariable String mail, @PathVariable Long enterpriseId) {
        enterpriseService.confirmEnterprise(enterpriseId);
        return ResponseEntity.ok()
                .body(modelMapper.map(workersService.confirmWorker(mail, enterpriseId), WorkerApi.class));

    }

}
