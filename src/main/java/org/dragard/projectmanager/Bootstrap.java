package org.dragard.projectmanager;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.command.Command;
import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.command.*;
import org.dragard.projectmanager.repository.ProjectRepositoryImpl;
import org.dragard.projectmanager.repository.TaskRepositoryImpl;
import org.dragard.projectmanager.service.ProjectServiceImpl;
import org.dragard.projectmanager.service.TaskServiceImpl;

import java.util.*;

public class Bootstrap implements ServiceLocator {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final TaskService taskService;

    private final Map<String, Command> commandList;
    private final Scanner scanner;

    public Bootstrap() {
        projectRepository = new ProjectRepositoryImpl();
        taskRepository = new TaskRepositoryImpl();
        taskService = new TaskServiceImpl(taskRepository, projectRepository);
        projectService = new ProjectServiceImpl(projectRepository);
        commandList = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    private void registry(Class clazz) throws IllegalAccessException, InstantiationException {
        AbstractCommand command = (AbstractCommand) clazz.newInstance();
        command.setServiceLocator(this);
        commandList.put(command.getName(), command);
    }

    public void registry(Class[] clazzes) throws IllegalAccessException, InstantiationException {
        for (Class clazz:
             clazzes) {
            registry(clazz);
        }
    }

    public void run(){
        while (true) {
            System.out.println("\nEnter your command (enter \"help\" for list of commands)");
            final String input = scanner.nextLine().toLowerCase();
            if (commandList.containsKey(input)){
                commandList.get(input).execute();
            } else {
                System.out.println("Command not recognized. Try again, please");
            }
        }
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    @Override
    public Scanner getScanner() {
        return scanner;
    }

    @Override
    public Map<String, Command> getCommandList() {
        return commandList;
    }

    @Override
    public ProjectService getProjectService() {
        return projectService;
    }

    @Override
    public TaskService getTaskService() {
        return taskService;
    }
}
