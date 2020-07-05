package com.utrechtfour.supermarket.errors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;


public class ApiError{
    private String status;
    private String message;
    private List<FieldError> fieldErrors;

    public ApiError(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiError(String status, String message, List<FieldError> fieldErrors) {
        this.status = status;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
