package com.eduardoschelive.digiobackend.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class DigioAPIException extends ApplicationException {

    public DigioAPIException(String endpoint) {
        super("Ocorreu um erro ao consultar a API da Digio no endpoint:  " + endpoint);
    }

    @Override
    public String getErrorCode() {
        return "DIGIO_API_ERROR";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.SERVICE_UNAVAILABLE;
    }
}
