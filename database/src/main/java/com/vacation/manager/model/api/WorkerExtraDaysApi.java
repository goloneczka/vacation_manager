package com.vacation.manager.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;


@Data
@AllArgsConstructor
public class WorkerExtraDaysApi {


    @Min(value = 0, message = "Wymagany format w postaci liczby" )
    private int seniority;
    @Min(value = 0, message = "Wymagany format w postaci liczby" )
    private int extraDays;
    @Min(value = 0, message = "Wymagany format w postaci liczby" )
    private int annualExtraDays;

    public WorkerExtraDaysApi() { }


}
