package com.vacation.manager.model;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Enterprise {

    private Long id;
    private String enterpriseName;
    private Integer freeDays;
    private Boolean confirmed;

    public Enterprise() { }
}
