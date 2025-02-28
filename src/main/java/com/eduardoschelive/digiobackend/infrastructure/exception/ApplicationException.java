package com.eduardoschelive.digiobackend.infrastructure.exception;

import org.springframework.http.HttpStatus;

public abstract class ApplicationException extends RuntimeException {

    /**
     * @param message the message of the exception
     */
    protected ApplicationException(String message) {
        super(message);
    }

    /**
     * @return the error code of the exception (e.g. "CIRCUIT_BREAKER")
     */
    public abstract String getErrorCode();

    /**
     * @return the {@link HttpStatus} corresponding to the exception
     */
    public abstract HttpStatus getHttpStatus();

}
