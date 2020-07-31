package com.vacation.manager.model.api;

import com.vacation.manager.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
public class WorkerApi {

    private String email;
    private String name;
    private String occupation;
    private Long enterpriseId;
    private Long employeeVarsId;
    private List<Role> roles;
    private String hired;


    public WorkerApi() { }

    public void setHiredFromLocalDate(LocalDate startDate1){
        hired = startDate1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
