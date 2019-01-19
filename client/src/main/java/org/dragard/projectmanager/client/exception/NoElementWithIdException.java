package org.dragard.projectmanager.client.exception;

public class NoElementWithIdException extends AbstractRunTimeTaskManagerException {

    public NoElementWithIdException() {
        super("No element with this id");
    }
}
