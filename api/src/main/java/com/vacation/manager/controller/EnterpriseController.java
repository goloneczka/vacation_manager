package com.vacation.manager.controller;

import com.vacation.manager.exception.AppException;
import com.vacation.manager.model.Enterprise;
import com.vacation.manager.model.Worker;
import com.vacation.manager.model.api.RegisterForm;
import com.vacation.manager.response.ResponseStatus;
import com.vacation.manager.service.EnterpriseService;
import com.vacation.manager.service.WorkersService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;
    private final WorkersService workersService;
    private final ModelMapper modelMapper;

    public EnterpriseController(EnterpriseService enterpriseService, WorkersService workersService, ModelMapper modelMapper) {
        this.enterpriseService = enterpriseService;
        this.workersService = workersService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/enterprise")
    @Transactional(rollbackFor = AppException.class)
    public ResponseEntity<String> createEnterprise(@RequestBody RegisterForm registerForm){
        Worker tmpWorker = modelMapper.map(registerForm, Worker.class);
        Enterprise enterprise = enterpriseService.createEnterprise(modelMapper.map(registerForm, Enterprise.class));
        tmpWorker.setEnterpriseId(enterprise.getId());
        workersService.createRoleToWorker(workersService.createWorker(tmpWorker).getId().intValue(), Arrays.asList(1, 2, 3));
        return ResponseEntity.status(ResponseStatus.OK).body("SUCCESS");
    }
}
