package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Application;

import java.util.Scanner;

public class CreateTaskCommand extends AbstractCommand{

    public CreateTaskCommand(Application application) {
        super("create_task", "Create new task and make it active", application);
    }

    @Override
    public void execute() {
        if (getApplication().getProjectRepository().getActive() == null){
            System.out.println(Application.NO_ACTIVE_PROJECT_MESSAGE);
            return;
        }
        Scanner scanner = getApplication().getScanner();
        System.out.println("Enter task name: ");
        final String name = scanner.nextLine();
        System.out.println("Enter task description: ");
        final String description = scanner.nextLine();
        getApplication().getTaskRepository().create(name, description);
    }
}
