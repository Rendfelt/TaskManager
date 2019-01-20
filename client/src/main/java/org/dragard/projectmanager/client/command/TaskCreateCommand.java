package org.dragard.projectmanager.client.command;

import org.dragard.projectmanager.client.endpoint.Response;

import java.util.Scanner;

public class TaskCreateCommand extends AbstractCommand{

    public TaskCreateCommand() {
        super("create_task", "Create new task", true);
    }

    @Override
    public void execute() {
        Scanner scanner = getServiceLocator().getScanner();
        try {
            System.out.println("Enter project id: ");
            final String projectId = scanner.nextLine();
            System.out.println("Enter task name: ");
            final String name = scanner.nextLine();
            System.out.println("Enter task description: ");
            final String description = scanner.nextLine();
            Response response = getServiceLocator().getTaskService().create(name, description, projectId,
                    getServiceLocator().getAuthorizationService().getToken());
            if (response.getMessage() != null) {
                System.out.println(response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
