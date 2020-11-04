package com.vacation.manager.model.api.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class RegisterEmployeeForm extends  RegisterTemplate{

    private Boolean isHR;
    private String enterpriseName;


    public RegisterEmployeeForm() { }
}
