package org.dragard.projectmanager.command;

import org.dragard.projectmanager.endpoint.Response;

import java.util.Scanner;

public class TaskCreateCommand extends AbstractCommand{

    public TaskCreateCommand() {
        super("create_task", "Create new task", true);
    }

    @Override
    public void execute() {
        System.out.println("Enter project id: ");
        final String projectId = getServiceLocator().getInterfaceService().getNewLine();
        System.out.println("Enter task name: ");
        final String name = getServiceLocator().getInterfaceService().getNewLine();
        System.out.println("Enter task description: ");
        final String description = getServiceLocator().getInterfaceService().getNewLine();
        Response response = getServiceLocator().getTaskService().create(name, description, projectId,
                getServiceLocator().getAuthorizationService().getToken());
        if (response.getMessage() != null) {
            System.out.println(response.getMessage());
        }
    }
}
