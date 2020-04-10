package com.vacation.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @GetMapping("/")
    public String getRunApp() {
        return "Empty.JSON 123";
    }

    @GetMapping("/logos")
    public String getAdmin() {
        return "Empty.JSON";
    }
}
