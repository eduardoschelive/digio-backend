package com.eduardoschelive.digiobackend.application.exception;

public class NoPurchasesFoundForCustomerException extends DomainException {

    private final String document;

    public NoPurchasesFoundForCustomerException(String document) {
        super("No purchases found for customer with document " + document);
        this.document = document;
    }

    public String getDocument() {
        return document;
    }
}
