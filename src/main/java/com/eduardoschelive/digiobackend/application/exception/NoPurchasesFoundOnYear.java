package com.eduardoschelive.digiobackend.application.exception;

public class NoPurchasesFoundOnYear extends DomainException {
    public NoPurchasesFoundOnYear(Integer year) {
        super("No purchases found on year " + year);
    }
}
