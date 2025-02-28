package com.eduardoschelive.digiobackend.adapter.customer.inbound;

import com.eduardoschelive.digiobackend.infrastructure.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class NoPurchasesFoundForCustomerApplicationException extends ApplicationException {

    public NoPurchasesFoundForCustomerApplicationException(String document) {
        super("Não foi possível localizar nenhuma compra para o cliente com documento " + document);
    }

    @Override
    public String getErrorCode() {
        return "NO_PURCHASES_FOUND_FOR_CUSTOMER";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
