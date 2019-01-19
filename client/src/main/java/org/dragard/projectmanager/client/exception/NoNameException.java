package org.dragard.projectmanager.client.exception;

public class NoNameException extends AbstractRunTimeTaskManagerException {

    public NoNameException() {
        super("Name cannot be empty");
    }
}
