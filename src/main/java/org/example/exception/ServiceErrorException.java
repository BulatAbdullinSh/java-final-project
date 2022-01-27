package org.example.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class ServiceErrorException extends Throwable {
    public ServiceErrorException() {
    }

    public ServiceErrorException(String message) {
        super(message);
    }

    public ServiceErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceErrorException(Throwable cause) {
        super(cause);
    }

    public ServiceErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceErrorException(EmptyResultDataAccessException e) {

    }
}
