package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Application;
import org.dragard.projectmanager.exception.NoActiveElementException;

import java.util.Scanner;

public class UpdateProjectCommand extends AbstractCommand{

    public UpdateProjectCommand(Application application) {
        super("update_project", "Update active project", application);
    }

    @Override
    public void execute() {
        if (getApplication().getProjectRepository().getActive() == null){
            System.out.println(Application.NO_ACTIVE_PROJECT_MESSAGE);
            return;
        }
        Scanner scanner = getApplication().getScanner();
        System.out.println("Enter new project name:");
        final String name = scanner.nextLine();
        System.out.println("Enter project description:");
        final String description = scanner.nextLine();
        try {
            getApplication().getProjectRepository().update(name, description);
        } catch (NoActiveElementException e) {
            System.out.println(Application.NO_ACTIVE_PROJECT_MESSAGE);
        }
    }
}
