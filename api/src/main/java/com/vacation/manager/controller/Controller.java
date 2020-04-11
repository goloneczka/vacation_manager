package com.vacation.manager.controller;

import com.vacation.manager.model.Enterprise;
import com.vacation.manager.service.EnterpriseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    EnterpriseService enterpriseService;

    public Controller(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @GetMapping("/")
    public String getRunApp() {
        return "Empty.JSON 123";
    }

    @GetMapping("/logos")
    public String getAdmin() {
        return "Empty.JSON";
    }

    @GetMapping("/enterprises")
    public List<Enterprise> getEnterprises(){
        return enterpriseService.getEnterpriseRepository().findAll();
    }
}

