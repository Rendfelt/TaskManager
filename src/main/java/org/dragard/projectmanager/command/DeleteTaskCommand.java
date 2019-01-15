package org.dragard.projectmanager.command;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.exception.NoElementWithIdException;

import java.util.Scanner;

public class DeleteTaskCommand extends AbstractCommand{

    public DeleteTaskCommand(ServiceLocator serviceLocator) {
        super("delete_task", "Delete active task", serviceLocator);
    }

    @Override
    public void execute() {
        Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Enter task id:");
        try {
            getServiceLocator().getTaskService().delete(scanner.nextLine());
        } catch (NoElementWithIdException e) {
            e.printStackTrace();
        }
    }
}
