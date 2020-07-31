package com.vacation.manager.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class WorkerExtraDaysApi {

    private int seniority;
    private int extraDays;
    private int annualExtraDays;

    public WorkerExtraDaysApi() { }


}
