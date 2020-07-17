package com.vacation.manager.model.api;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class EnterpriseApi {

    private String enterpriseName;
    private Integer freeDays;

    public EnterpriseApi() { }
}
