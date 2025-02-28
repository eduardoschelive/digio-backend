package com.eduardoschelive.digiobackend.adapter.customer.inbound;

import com.eduardoschelive.digiobackend.infrastructure.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class CustomerNotFoundApplicationException extends ApplicationException {

    public CustomerNotFoundApplicationException(String document) {
        super("Cliente não encontrado com o documento  " + document);
    }

    @Override
    public String getErrorCode() {
        return "CUSTOMER_NOT_FOUND";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
