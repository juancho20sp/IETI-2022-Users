package com.planner.users.service.exceptions;

public class UserServiceException extends Exception {
    public static final String USER_NOT_FOUND = "User not found in database";

    public UserServiceException() {
        super();
    }

    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(String message, Exception exception) {
        super(message, exception);
    }
}
