package com.vacation.manager.model.api;

import com.vacation.manager.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
public class WorkerApi {

    private String email;
    private String name;
    @Size(min = 2, message = "Wymagane stanowisko pracownika")
    private String occupation;
    private String enterpriseName;
    private Long employeeVarsId;
    private List<Role> roles;
    private String hired;


    public WorkerApi() { }

    public void setHiredFromLocalDate(LocalDate startDate1){
        hired = startDate1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
