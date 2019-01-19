package org.dragard.projectmanager.server.exception;

public abstract class AbstractRunTimeTaskManagerException extends Exception{

    public AbstractRunTimeTaskManagerException(String message) {
        super(message);
    }

    public AbstractRunTimeTaskManagerException() {
    }
}
