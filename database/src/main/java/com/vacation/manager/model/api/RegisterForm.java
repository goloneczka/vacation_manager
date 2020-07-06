package com.vacation.manager.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RegisterForm {

    @Size(min = 2, message = "Wymagane imie i nazwisko")
    private String name;
    @Size(min = 4, message = "Wymagany poprawny email")
    @Email(message = "Wymagany poprawny email")
    private String email;
    @Size(min = 2, message = "Wymagana nazwa firmy, conajmniej dwa znaki")
    private String enterpriseName;
    @Size(min = 6, message = "Wymagane hasło użytkownika, conajmniej sześć znaków")
    private String password;
    private String occupation;

    public RegisterForm() { }
}
