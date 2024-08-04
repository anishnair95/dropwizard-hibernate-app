package com.io.dropwizardhibernate.error;

public enum ErrorType {
    bad_request(400),
    unauthorized(401),
    forbidden(403),
    method_not_allowed(405),
    conflict(409),
    duplicate_value(409),
    not_found(404),
    locked(409),
    too_many_requests(429),
    internal_server_error(500),
    idempotency_key_in_use(429),
    request_failed(500),
    request_timeout(408),
    gateway_timeout(504),
    payment_gateway_timeout(504),
    payment_gateway_unavailable(502),
    payment_gateway_error(502),
    missing_parameter(400),
    invalid_field(400),
    not_implemented(501);

    private final int httpStatusCode;

    ErrorType(int statusCode) {
        this.httpStatusCode = statusCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

}