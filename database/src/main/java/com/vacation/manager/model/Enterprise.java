package com.vacation.manager.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Data
@AllArgsConstructor
public class Enterprise {

    private String name;
    private Integer freeDays;
    private Boolean confirmed;
    private LocalDate restartTime;

    public Enterprise() { }

    public void setRestartFromString(String startDate1){
        if (startDate1 == null)
            return;
        restartTime = LocalDate.parse(startDate1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
