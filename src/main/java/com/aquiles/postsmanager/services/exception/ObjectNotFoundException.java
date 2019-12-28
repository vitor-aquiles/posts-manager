package com.aquiles.postsmanager.services.exception;

public class ObjectNotFoundException extends RuntimeException {
    private static final Long serialVersionID = 1L;

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
