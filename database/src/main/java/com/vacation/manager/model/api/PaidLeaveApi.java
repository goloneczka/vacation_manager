package com.vacation.manager.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class PaidLeaveApi {

    private Long id;
    private String startDate;
    private String endDate;
    private String describe;
    private float days;
    private String status;

    public PaidLeaveApi() { }

    public void setStartDateFromLocalDate(LocalDate startDate1){
        startDate = startDate1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setEndDateFromLocalDate(LocalDate startDate1){
        endDate = startDate1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
