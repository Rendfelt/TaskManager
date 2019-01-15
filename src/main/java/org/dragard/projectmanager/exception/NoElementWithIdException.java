package org.dragard.projectmanager.exception;

public class NoElementWithIdException extends AbstractTaskManagerException {

    public NoElementWithIdException() {
        super("No element with this id");
    }
}
