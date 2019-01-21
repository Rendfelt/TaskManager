package org.dragard.projectmanager.exception;

public class NoNameException extends AbstractRunTimeTaskManagerException {

    public NoNameException() {
        super("Name cannot be empty");
    }
}
