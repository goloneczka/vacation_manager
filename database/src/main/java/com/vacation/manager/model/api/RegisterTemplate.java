package com.vacation.manager.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
abstract class RegisterTemplate {

    @Size(min = 2, message = "Wymagane imie i nazwisko")
    private String name;
    @Size(min = 4, message = "Wymagany poprawny email")
    @Email(message = "Wymagany poprawny email")
    private String email;
    @Size(min = 2, message = "Wymagane stanowisko pracownika")
    private String occupation;
    @Size(min = 10, max = 10, message = "Wymagana forma daty to yyyy-mm-dd")
    private String hired;

    public RegisterTemplate() { }

    public LocalDate convertHireStringToLocalDate(){
        return LocalDate.parse(hired, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
