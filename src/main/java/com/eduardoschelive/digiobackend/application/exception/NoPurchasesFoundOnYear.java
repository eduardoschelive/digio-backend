package com.eduardoschelive.digiobackend.application.exception;

public class NoPurchasesFoundOnYear extends DomainException {

    private final Integer year;

    public NoPurchasesFoundOnYear(Integer year) {
        super("No purchases found on year " + year);
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }
}
