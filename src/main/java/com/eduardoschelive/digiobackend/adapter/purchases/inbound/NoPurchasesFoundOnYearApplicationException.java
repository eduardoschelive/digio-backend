package com.eduardoschelive.digiobackend.adapter.purchases.inbound;

import com.eduardoschelive.digiobackend.infrastructure.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class NoPurchasesFoundOnYearApplicationException extends ApplicationException {

    public NoPurchasesFoundOnYearApplicationException(Integer year) {
        super("Não foram encontradas compras no ano de " + year);
    }

    @Override
    public String getErrorCode() {
        return "NO_PURCHASES_FOUND_ON_YEAR";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
