package com.eduardoschelive.digiobackend.application.exception;

public abstract class DomainException extends Exception {
    protected DomainException(String message) {
        super(message);
    }
}
