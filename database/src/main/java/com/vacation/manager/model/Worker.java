package com.vacation.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Worker {


    private Long id;
    private String email;
    private String password;
    private String name;
    private String occupation;
    private Long enterpriseId;


    public Worker() { }

}
