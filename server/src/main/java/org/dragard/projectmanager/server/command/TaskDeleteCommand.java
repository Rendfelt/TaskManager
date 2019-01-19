package org.dragard.projectmanager.server.command;

import java.util.Scanner;

public class TaskDeleteCommand extends AbstractCommand{

    public TaskDeleteCommand() {
        super("delete_task", "Delete active task", true);
    }

    @Override
    public void execute() {
        final Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Enter task id:");
        getServiceLocator().getTaskService().delete(scanner.nextLine());
    }
}
