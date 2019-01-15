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

    private final static Class[] clazzes = {ShowTasksCommand.class,};

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
        commandList.put(new ShowTasksCommand(this).getName(), new ShowTasksCommand(this));
        commandList.put(new ShowProjectsCommand(this).getName(), new ShowProjectsCommand(this));
        commandList.put(new CreateProjectCommand(this).getName(), new CreateProjectCommand(this));
        commandList.put(new UpdateProjectCommand(this).getName(), new UpdateProjectCommand(this));
        commandList.put(new DeleteProjectCommand(this).getName(), new DeleteProjectCommand(this));
        commandList.put(new CreateTaskCommand(this).getName(), new CreateTaskCommand(this));
        commandList.put(new UpdateTaskCommand(this).getName(), new UpdateTaskCommand(this));
        commandList.put(new DeleteTaskCommand(this).getName(), new DeleteTaskCommand(this));
        commandList.put(new ExitCommand(this).getName(), new ExitCommand(this));
        commandList.put(new HelpCommand(this).getName(), new HelpCommand(this));

        scanner = new Scanner(System.in);
//        initializeTestData();
    }

/*    private void initializeTestData(){
        projectRepository.create("ProjectName1", "Description1");
        projectRepository.create("ProjectName2", "Description2");
        projectRepository.create("ProjectName3", "Description3");
        Project project = projectRepository.getElements().values().iterator().next();
        Project project2 = projectRepository.getElements().values().iterator().next();
        taskRepository.create(project.getId(), "TaskName1", "Description1");
        taskRepository.create(project.getId(), "TaskName2", "Description2");
        taskRepository.create(project2.getId(), "TaskName3", "Description3");
        taskRepository.create(project2.getId(), "TaskName5", "Description6");
    }*/
    private void registry(Class clazz) throws IllegalAccessException, InstantiationException {
        AbstractCommand command = (AbstractCommand) clazz.newInstance();
        command.setServiceLocator(this);
        commandList.put(command.getName(), command);
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
