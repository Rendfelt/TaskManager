package org.dragard.projectmanager.command;

import org.dragard.projectmanager.endpoint.Response;

import java.util.Scanner;

public class TaskDeleteCommand extends AbstractCommand{

    public TaskDeleteCommand() {
        super("delete_task", "Delete active task", true);
    }

    @Override
    public void execute() {
        try {
            final Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Enter task id:");
            final String id = scanner.nextLine();
            Response response = getServiceLocator().getTaskService().delete(id,
                    getServiceLocator().getAuthorizationService().getToken());
            if (response.getMessage() != null) {
                System.out.println(response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
