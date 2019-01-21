package org.dragard.projectmanager.exception;

public class NoElementWithIdException extends AbstractRunTimeTaskManagerException {

    public NoElementWithIdException() {
        super("No element with this id");
    }
}
