package org.dragard.projectmanager.command;

import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;

import java.util.Scanner;

public class CreateTaskCommand extends AbstractCommand{

    public CreateTaskCommand() {
        super("create_task", "Create new task");
    }

    @Override
    public void execute() {
        Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Enter project id: ");
        final String projectId = scanner.nextLine();
        System.out.println("Enter task name: ");
        final String name = scanner.nextLine();
        System.out.println("Enter task description: ");
        final String description = scanner.nextLine();
        try {
            getServiceLocator().getTaskService().create(name, description, projectId);
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (NoElementWithIdException e) {
            e.printStackTrace();
        }
    }
}
