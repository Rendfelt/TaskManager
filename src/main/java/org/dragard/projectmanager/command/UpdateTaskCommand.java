package org.dragard.projectmanager.command;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;

import java.util.Scanner;

public class UpdateTaskCommand extends AbstractCommand{

    public UpdateTaskCommand(ServiceLocator serviceLocator) {
        super("update_task", "Update active task", serviceLocator);
    }

    @Override
    public void execute() {
        try {
            Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Enter task id");
            Task task = getServiceLocator().getTaskService().getElementById(scanner.nextLine());
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
            getServiceLocator().getTaskService().update(task.getId(), name, description);
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (NoElementWithIdException e) {
            e.printStackTrace();
        }
    }
}
