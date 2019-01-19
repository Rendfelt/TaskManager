package org.dragard.projectmanager.server.exception;

public class NoElementWithIdException extends AbstractRunTimeTaskManagerException {

    public NoElementWithIdException() {
        super("No element with this id");
    }
}
