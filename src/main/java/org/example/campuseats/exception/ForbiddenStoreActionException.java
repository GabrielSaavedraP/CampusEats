package org.example.campuseats.exception;

public class ForbiddenStoreActionException extends RuntimeException {
    public ForbiddenStoreActionException(String message) {
        super(message);
    }
}
