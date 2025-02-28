package com.eduardoschelive.digiobackend.application.exception;

public class CustomerNotFoundException extends DomainException {

    private final String document;

    public CustomerNotFoundException(String document) {
        super("Customer not found with document " + document);
        this.document = document;
    }

    public String getDocument() {
        return document;
    }
}
