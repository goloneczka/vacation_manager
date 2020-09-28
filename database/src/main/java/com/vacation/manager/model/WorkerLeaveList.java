package com.vacation.manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkerLeaveList {

    private Long id;
    private String startDate;
    private String endDate;

    private String name;
    private String occupation;
    private String status;


    public WorkerLeaveList() { }


}
