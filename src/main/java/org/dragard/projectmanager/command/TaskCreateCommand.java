package org.dragard.projectmanager.command;

import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;

import java.util.Scanner;

public class TaskCreateCommand extends AbstractCommand{

    public TaskCreateCommand() {
        super("create_task", "Create new task");
    }

    @Override
    public void execute() {
        Scanner scanner = getServiceLocator().getScanner();
        try {
            System.out.println("Enter project id: ");
            final String projectId = scanner.nextLine();
            if (getServiceLocator().getProjectService().getElementById(projectId) == null){
                throw new NoElementWithIdException();
            }
            System.out.println("Enter task name: ");
            final String name = scanner.nextLine();
            if (name.isEmpty()){
                throw new NoNameException();
            }
            System.out.println("Enter task description: ");
            final String description = scanner.nextLine();
            getServiceLocator().getTaskService().create(name, description, projectId);
        } catch (NoNameException e) {
            e.printStackTrace();
        } catch (NoElementWithIdException e) {
            e.printStackTrace();
        }
    }
}
