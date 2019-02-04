package org.dragard.projectmanager.command;

import org.dragard.projectmanager.endpoint.Response;

import java.util.Scanner;

public class TaskUpdateCommand extends AbstractCommand{

    public TaskUpdateCommand() {
        super("update_task", "Update active task", true);
    }

    @Override
    public void execute() {
        System.out.println("Enter task id");
        final String id = getServiceLocator().getInterfaceService().getNewLine();
        System.out.println("Enter task name:");
        final String name = getServiceLocator().getInterfaceService().getNewLine();
        System.out.println("Enter task description");
        final String description = getServiceLocator().getInterfaceService().getNewLine();
        Response response = getServiceLocator().getTaskService().update(id, name, description,
                getServiceLocator().getAuthorizationService().getToken());
        if (response.getMessage() != null) {
            System.out.println(response.getMessage());
        }
    }
}
