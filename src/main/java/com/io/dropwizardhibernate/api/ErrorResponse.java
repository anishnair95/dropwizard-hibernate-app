package com.io.dropwizardhibernate.api;

import com.io.dropwizardhibernate.error.ErrorCode;
import com.io.dropwizardhibernate.error.ErrorMessage;
import com.io.dropwizardhibernate.error.ErrorType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorResponse {

    private ErrorType type;
    private List<ErrorMessage> errorMessages;

}
