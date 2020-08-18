package com.vacation.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class WorkerExtraDays {


    private Long id;
    private int seniority;
    private int extraDays;
    private int annualExtraDays;
    private int transitiveDays;

    public WorkerExtraDays() { }


}
