package com.vacation.manager.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.Set;


@Data
@AllArgsConstructor
public class Enterprise {

    private Long id;
    private String enterpriseName;
    private Integer freeDays;
    private Boolean confirmed;

    @OneToMany(mappedBy="enterprise")
    private Set<Worker> workers;

    public Enterprise() { }
}
