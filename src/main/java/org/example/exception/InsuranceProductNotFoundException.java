package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InsuranceProductNotFoundException extends RuntimeException{
    public InsuranceProductNotFoundException() {
    }

    public InsuranceProductNotFoundException(String message) {
        super(message);
    }

    public InsuranceProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsuranceProductNotFoundException(Throwable cause) {
        super(cause);
    }

    public InsuranceProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
