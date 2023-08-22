package com.phonepe.calendar.exceptions;

public class UserNotAllowedException extends RuntimeException {

    public UserNotAllowedException(String message) {
        super(message);
    }
}
