package org.dragard.projectmanager.command;

import org.dragard.projectmanager.exception.NoNameException;

import java.util.Scanner;

public class ProjectCreateCommand extends AbstractCommand{

    public ProjectCreateCommand() {
        super("create_project", "Create new project", true);
    }

    @Override
    public void execute() throws Exception {
        try {
            final Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Enter project name:");
            final String name = scanner.nextLine();
            if (name.isEmpty()){
                throw new NoNameException();
            }
            System.out.println("Enter project description:");
            final String description = scanner.nextLine();
            getServiceLocator().getProjectService().create(name, description,
                    getServiceLocator().getAuthorizationService().getActiveUser().getId());
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
