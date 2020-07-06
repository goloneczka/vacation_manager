package com.vacation.manager.exception;


import com.vacation.manager.response.ErrorResponse;
import com.vacation.manager.response.ResponseStatus;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException e, WebRequest webRequest) {
        return ResponseEntity
                .status(ResponseStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getErrors()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherException(Exception e, WebRequest webRequest) {
        return ResponseEntity
                .status(ResponseStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(List.of(e.getMessage())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException  e, WebRequest request) {
        return ResponseEntity
                .status(ResponseStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList())));

    }
}
