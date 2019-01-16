package org.dragard.projectmanager;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.command.Command;
import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.command.*;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.exception.NoNameException;
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
        taskService = new TaskServiceImpl(taskRepository);
        projectService = new ProjectServiceImpl(projectRepository);
        commandList = new HashMap<>();
        scanner = new Scanner(System.in);
        initializeTestData();
    }

    private void initializeTestData(){
        try {
            projectService.create("ProjectName1", "Description1");
            projectService.create("ProjectName2", "Description2");
            projectService.create("ProjectName3", "Description3");
            projectService.create("ProjectName4", "Description4");
            Iterator<Project> iterator = projectService.getElements().values().iterator();
            final Project project1 = iterator.next();
            final Project project2 = iterator.next();
            taskService.create("TaskName1", "TaskDescription1", project1.getId());
            taskService.create("TaskName2", "TaskDescription2", project1.getId());
            taskService.create("TaskName3", "TaskDescription3", project1.getId());
            taskService.create("TaskName4", "TaskDescription4", project2.getId());
            taskService.create("TaskName5", "TaskDescription5", project2.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
