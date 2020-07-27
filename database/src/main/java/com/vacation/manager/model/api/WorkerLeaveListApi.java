package com.vacation.manager.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkerLeaveListApi {

    private Long id;
    private String startDate;
    private String endDate;

    private String name;
    private String occupation;

    public WorkerLeaveListApi() { }


}
