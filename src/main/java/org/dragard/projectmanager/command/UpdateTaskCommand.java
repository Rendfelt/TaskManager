package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Application;
import org.dragard.projectmanager.exception.NoActiveElementException;

import java.util.Scanner;

public class UpdateTaskCommand extends AbstractCommand{

    public UpdateTaskCommand(Application application) {
        super("update_task", "Update active task", application);
    }

    @Override
    public void execute() {
        if (getApplication().getTaskRepository().getActive() == null){
            System.out.println(Application.NO_ACTIVE_PROJECT_MESSAGE);
            return;
        }
        Scanner scanner = getApplication().getScanner();
        System.out.println("Enter new task name:");
        final String name = scanner.nextLine();
        System.out.println("Enter task description:");
        final String description = scanner.nextLine();
        try {
            getApplication().getTaskRepository().update(name, description);
        } catch (NoActiveElementException e) {
            System.out.println(Application.NO_ACTIVE_PROJECT_MESSAGE);
        }
    }
}
