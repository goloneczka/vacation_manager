package com.vacation.manager.exception;

import java.util.ArrayList;
import java.util.List;

public class AppExceptionBuilder {
    private List<String> errors;

    public AppExceptionBuilder(){
        this.errors = new ArrayList<>();
    }

    public AppExceptionBuilder addError(String message){
        errors.add(message);
        return this;
    }

    public AppException build(){
        AppException appException = new AppException();
        appException.setErrors(errors);
        return appException;
    }
}
