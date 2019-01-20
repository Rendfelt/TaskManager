package org.dragard.projectmanager.client.command;

import org.dragard.projectmanager.client.endpoint.Response;

import java.util.Scanner;

public class ProjectUpdateCommand extends AbstractCommand{

    public ProjectUpdateCommand() {
        super("update_project", "Update active project", true);
    }

    @Override
    public void execute() {
        try {
            final Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Enter project id");
            String id = scanner.nextLine();
            System.out.println("Enter project name:");
            final String name = scanner.nextLine();
            System.out.println("Enter project description");
            final String description = scanner.nextLine();
            Response response = getServiceLocator().getProjectService().update(id, name, description,
                    getServiceLocator().getAuthorizationService().getToken());
            if (response.getMessage() != null) {
                System.out.println(response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
