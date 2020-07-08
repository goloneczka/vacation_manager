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
    public String getRunApp() { return "{}"; }

    @GetMapping("/HR/eloo")
    public String getAdmin() {
        return "{}";
    }

    @GetMapping("/employee/eloo")
    public String getEmployee() {
        return "{}";
    }

    @GetMapping("/eloo")
    public String getSMTH() {
        return "Empty.JSON";
    }




}

