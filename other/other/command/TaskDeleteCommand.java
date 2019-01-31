package org.dragard.projectmanager.command;

import java.util.Scanner;

public class TaskDeleteCommand extends AbstractCommand{

    public TaskDeleteCommand() {
        super("delete_task", "Delete active task", true);
    }

    @Override
    public void execute() throws Exception {
        final Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Enter task id:");
        try {
            getServiceLocator().getTaskService().delete(scanner.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
