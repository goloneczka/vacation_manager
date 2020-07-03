package com.vacation.manager.controller;

import com.vacation.manager.service.EnterpriseService;
import com.vacation.manager.service.WorkersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    EnterpriseService enterpriseService;
    WorkersService workersService;

    public Controller(EnterpriseService enterpriseService, WorkersService workersService) {
        this.enterpriseService = enterpriseService;
        this.workersService = workersService;
    }

    @GetMapping("/")
    public String getRunApp() {
        return "Empty.JSON 123";
    }

    @GetMapping("/HR/eloo")
    public String getAdmin() {
        return "Empty.JSON HR + ADMIN";
    }

    @GetMapping("/employee/eloo")
    public String getEmployee() {
        return "Empty.JSON employee + HR + ADMIN";
    }

    @GetMapping("/eloo")
    public String getSMTH() {
        return "Empty.JSON";
    }




}

