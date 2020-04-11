package com.vacation.manager.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "enterprise")
@Data
public class Enterprise {

    private Long id;
    private String name;
    private Integer freeDays;

    public Enterprise() { }
}
