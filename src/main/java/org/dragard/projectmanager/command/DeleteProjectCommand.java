package org.dragard.projectmanager.command;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.exception.NoElementWithIdException;

import java.util.Scanner;

public class DeleteProjectCommand extends AbstractCommand{

    public DeleteProjectCommand(ServiceLocator serviceLocator) {
        super("delete_project", "Delete active project", serviceLocator);
    }

    @Override
    public void execute() {
        Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Enter project id:");
        final String projectId = scanner.nextLine();
        try {
            getServiceLocator().getProjectService().delete(projectId);
            getServiceLocator().getTaskService().deleteTasksByProjectId(projectId);
        } catch (NoElementWithIdException e) {
            e.printStackTrace();
        }
    }
}
