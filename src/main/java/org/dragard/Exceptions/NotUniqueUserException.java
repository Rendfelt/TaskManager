package org.dragard.Exceptions;

public class NotUniqueUserException extends AbstractTaskManagerException{

    public NotUniqueUserException() {
    }

    public NotUniqueUserException(String message) {
        super(message);
    }
}
