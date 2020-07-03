package com.vacation.manager.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AppException extends RuntimeException{
    private List<String> errors;
}
