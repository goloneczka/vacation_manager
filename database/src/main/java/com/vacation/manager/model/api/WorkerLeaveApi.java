package com.vacation.manager.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class WorkerLeaveApi extends WorkerLeaveListApi {

    private String describe;
    private float days;
    private String status;


    public WorkerLeaveApi() { }


}
