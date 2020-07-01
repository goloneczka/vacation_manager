package com.vacation.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
public class Worker {


    private Long id;
    private String username;
    private String password;
    private String name;
    private String occupation;


    public Worker() { }

}
