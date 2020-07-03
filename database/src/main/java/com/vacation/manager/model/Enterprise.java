package com.vacation.manager.model;


import lombok.Data;

import javax.persistence.OneToMany;
import java.util.Set;


@Data
public class Enterprise {

    private Long id;
    private String enterpriseName;
    private Integer freeDays;

    @OneToMany(mappedBy="enterprise")
    private Set<Worker> workers;

    public Enterprise() { }
}
