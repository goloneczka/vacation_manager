package com.vacation.manager.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterForm {

    private String name;
    private String email;
    private String enterpriseName;
    private String password;
    private String occupation;

    public RegisterForm() { }
}
