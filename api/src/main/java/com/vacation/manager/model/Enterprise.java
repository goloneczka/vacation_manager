package com.vacation.manager.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enterprise", schema = "enterprises")
@Data
public class Enterprise {

    @Id
    private Long id;
    private String name;
    private Integer freeDays;

    public Enterprise() { }
}
