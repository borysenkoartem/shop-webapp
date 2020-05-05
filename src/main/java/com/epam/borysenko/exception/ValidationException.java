package com.epam.borysenko.exception;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = -687419263281109826L;

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

}
