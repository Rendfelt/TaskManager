package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Application;

import java.util.Scanner;

public class CreateProjectCommand extends AbstractCommand{

    public CreateProjectCommand(Application application) {
        super("create_project", "Create new project and make it active", application);
    }

    @Override
    public void execute() {
        Scanner scanner = getApplication().getScanner();
        System.out.println("Enter project name: ");
        final String name = scanner.nextLine();
        System.out.println("Enter project description: ");
        final String desctiption = scanner.nextLine();
        getApplication().getProjectRepository().create(name, desctiption);
    }
}
