package org.dragard.projectmanager.exception;

public abstract class AbstractTaskManagerException extends Exception{

    public AbstractTaskManagerException(String message) {
        super(message);
    }

    public AbstractTaskManagerException() {
    }
}
