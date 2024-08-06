package com.io.dropwizardhibernate.error;
public enum ErrorCode {
    invalid_parameter,
    invalid_parameter_integer,
    invalid_parameter_number,
    invalid_parameter_boolean,
    invalid_parameter_string,
    unknown_parameter,
    missing_parameter,
    parameters_exclusive,
    invalid_request,
    not_found,
    already_exists,
    card_error,
    authorization_token_expired,
    resource_not_found,
    in_use,
    invalid_value,
    delete_not_allowed,
    unexpected_error,
    db_error
}