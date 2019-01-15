package org.dragard.projectmanager.exception;

public class NoNameException extends AbstractTaskManagerException {

    public NoNameException() {
        super("Name cannot be empty");
    }
}
