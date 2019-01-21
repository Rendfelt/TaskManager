package org.dragard.projectmanager.command;

import org.dragard.projectmanager.endpoint.Response;

import java.util.Scanner;

public class ProjectDeleteCommand extends AbstractCommand{

    public ProjectDeleteCommand() {
        super("delete_project", "Delete active project", true);
    }

    @Override
    public void execute() {
        try {
            Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Enter project id:");
            final String id = scanner.nextLine();
            Response response = getServiceLocator().getProjectService().delete(id, getServiceLocator().getAuthorizationService().getToken());
            if (response.getMessage() != null) {
                System.out.println(response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
