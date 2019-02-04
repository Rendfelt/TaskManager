package org.dragard.projectmanager.command;

import org.dragard.projectmanager.endpoint.Response;

import java.util.Scanner;

public class ProjectUpdateCommand extends AbstractCommand{

    public ProjectUpdateCommand() {
        super("update_project", "Update active project", true);
    }

    @Override
    public void execute() {
        try {
            System.out.println("Enter project id");
            String id = getServiceLocator().getInterfaceService().getNewLine();
            System.out.println("Enter project name:");
            final String name = getServiceLocator().getInterfaceService().getNewLine();
            System.out.println("Enter project description");
            final String description = getServiceLocator().getInterfaceService().getNewLine();
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
