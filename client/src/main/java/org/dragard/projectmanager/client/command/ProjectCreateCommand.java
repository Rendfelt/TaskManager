package org.dragard.projectmanager.client.command;

import org.dragard.projectmanager.client.endpoint.Response;
import org.dragard.projectmanager.client.exception.NoNameException;

import java.util.Scanner;

public class ProjectCreateCommand extends AbstractCommand{

    public ProjectCreateCommand() {
        super("create_project", "Create new project", true);
    }

    @Override
    public void execute() {
        try {
            final Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Enter project name:");
            final String name = scanner.nextLine();
            if (name.isEmpty()){
                throw new NoNameException();
            }
            System.out.println("Enter project description:");
            final String description = scanner.nextLine();
            Response response = getServiceLocator().getProjectService().create(name, description,
                    getServiceLocator().getAuthorizationService().getToken());
            if (response.getMessage() != null) {
                System.out.println(response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
