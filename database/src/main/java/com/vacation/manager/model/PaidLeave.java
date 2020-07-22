package com.vacation.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class PaidLeave {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String describe;
    private float days;
    private Long employeeId;
    private String status;


    public PaidLeave() { }

    public void setStartDateFromString(String startDate1){
        startDate = LocalDate.parse(startDate1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setEndDateFromString(String startDate1){
        endDate = LocalDate.parse(startDate1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
