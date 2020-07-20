package com.vacation.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
public class Worker {


    private Long id;
    private String email;
    private String password;
    private String name;
    private String occupation;
    private Long enterpriseId;
    private Boolean confirmed;
    private LocalDate hired;


    private List<Role> roles;


    public Worker() { }

}
