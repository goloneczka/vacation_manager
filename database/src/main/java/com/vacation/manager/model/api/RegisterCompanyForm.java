package com.vacation.manager.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class RegisterCompanyForm extends RegisterTemplate{

//    @Size(min = 2, message = "Wymagana nazwa firmy, conajmniej dwa znaki")
    private String enterpriseName;
//    @Size(min = 6, message = "Wymagane hasło użytkownika, conajmniej sześć znaków")
    private String password;

    public RegisterCompanyForm() { }
}
