package org.dragard.projectmanager.server.exception;

public class NoNameException extends AbstractRunTimeTaskManagerException {

    public NoNameException() {
        super("Name cannot be empty");
    }
}
