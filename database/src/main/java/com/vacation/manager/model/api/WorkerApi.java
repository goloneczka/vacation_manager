package com.vacation.manager.model.api;

import com.vacation.manager.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WorkerApi {

    private String email;
    private String name;
    private String occupation;
    private Long enterpriseId;
    private List<Role> roles;

    public WorkerApi() { }

}
