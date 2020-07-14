package com.vacation.manager.controller;

import com.vacation.manager.exception.AppException;
import com.vacation.manager.model.Enterprise;
import com.vacation.manager.model.Worker;
import com.vacation.manager.model.api.RegisterForm;
import com.vacation.manager.service.EmailService;
import com.vacation.manager.service.EnterpriseService;
import com.vacation.manager.service.WorkersService;
import org.apache.commons.mail.EmailException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Enterprise> createEnterprise(@Valid @RequestBody RegisterForm registerForm){
        Worker tmpWorker = modelMapper.map(registerForm, Worker.class);
        Enterprise enterprise = enterpriseService.createEnterprise(modelMapper.map(registerForm, Enterprise.class));
        tmpWorker.setEnterpriseId(enterprise.getId());
        workersService.createRoleToWorker(workersService.createWorker(tmpWorker).getId().intValue(), Arrays.asList(1, 2, 3));
        return ResponseEntity.ok().body(enterprise);
    }

    @PostMapping("/email")
    public ResponseEntity<Enterprise> createEmail() throws EmailException {
        emailService.sendEmailMessage();
        return ResponseEntity.ok().body(new Enterprise());

    }
}
