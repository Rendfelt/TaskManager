package org.dragard.projectmanager.command;

import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;

import java.util.Scanner;

public class TaskUpdateCommand extends AbstractCommand{

    public TaskUpdateCommand() {
        super("update_task", "Update active task", true);
    }

    @Override
    public void execute() throws Exception {
        try {
            final Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Enter task id");
            final Task task = getServiceLocator().getTaskService().getElementById(scanner.nextLine());
            if (task == null){
                throw new NoElementWithIdException();
            }
            System.out.println("Enter task name:");
            final String name = scanner.nextLine();
            if (name.isEmpty()){
                throw new NoNameException();
            }
            System.out.println("Enter task description");
            final String description = scanner.nextLine();
            getServiceLocator().getTaskService().update(task.getId(), name, description,
                    getServiceLocator().getAuthorizationService().getActiveUser().getId());
        } catch (NoNameException | NoElementWithIdException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
