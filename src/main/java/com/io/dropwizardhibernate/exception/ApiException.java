package com.io.dropwizardhibernate.exception;

import com.io.dropwizardhibernate.error.ErrorMessage;
import com.io.dropwizardhibernate.error.ErrorType;
import lombok.Data;

import java.util.List;

public class ApiException extends RuntimeException {
    private ErrorType type;
    private List<ErrorMessage> errorMessages;

    public ApiException(ErrorType type, List<ErrorMessage> errorMessages) {
        super();
        this.type = type;
        this.errorMessages = errorMessages;
    }

    public ErrorType getType() {
        return type;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }
}
