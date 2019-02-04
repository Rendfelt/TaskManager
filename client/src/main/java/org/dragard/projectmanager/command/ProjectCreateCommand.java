package org.dragard.projectmanager.command;

import org.dragard.projectmanager.endpoint.Response;
import org.dragard.projectmanager.exception.NoNameException;

import java.util.Scanner;

public class ProjectCreateCommand extends AbstractCommand{

    public ProjectCreateCommand() {
        super("create_project", "Create new project", true);
    }

    @Override
    public void execute() {
        try {
            System.out.println("Enter project name:");
            final String name = getServiceLocator().getInterfaceService().getNewLine();
            if (name.isEmpty()){
                throw new NoNameException();
            }
            System.out.println("Enter project description:");
            final String description = getServiceLocator().getInterfaceService().getNewLine();
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
