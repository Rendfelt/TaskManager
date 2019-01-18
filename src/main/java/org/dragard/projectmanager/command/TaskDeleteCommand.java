package org.dragard.projectmanager.command;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class TaskDeleteCommand extends AbstractCommand{

    public TaskDeleteCommand() {
        super("delete_task", "Delete active task", true);
    }

    @Override
    public void execute() throws NoSuchAlgorithmException, URISyntaxException, IOException {
        final Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Enter task id:");
        getServiceLocator().getTaskService().delete(scanner.nextLine());
    }
}
