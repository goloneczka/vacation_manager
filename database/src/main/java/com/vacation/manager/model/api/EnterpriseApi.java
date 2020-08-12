package com.vacation.manager.model.api;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Data
@AllArgsConstructor
public class EnterpriseApi {

    private String enterpriseName;
    private Integer freeDays;
    private String restartTime;


    public EnterpriseApi() { }

    public void setRestartFromLocalDate(LocalDate startDate1){
        if (startDate1 == null)
            return;
        restartTime = startDate1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
