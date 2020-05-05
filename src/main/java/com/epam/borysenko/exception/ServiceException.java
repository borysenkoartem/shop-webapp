package com.epam.borysenko.exception;

public class ServiceException extends Exception{

    private static final long serialVersionUID = 5293270583573769757L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

}
