package org.dragard.projectmanager;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.command.Command;
import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.command.*;
import org.dragard.projectmanager.endpoint.AuthorizationEndpointImplService;
import org.dragard.projectmanager.endpoint.ProjectEndpointImplService;
import org.dragard.projectmanager.endpoint.TaskEndpointImplService;
import org.dragard.projectmanager.service.AuthorizationServiceImpl;
import org.dragard.projectmanager.service.ProjectServiceImpl;
import org.dragard.projectmanager.service.TaskServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Bootstrap implements ServiceLocator {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final AuthorizationService authorizationService;
    private final Map<String, Command> commandList;
    private final Scanner scanner;

    private final static Class[] classes = {TaskShowAllCommand.class, ProjectShowAllCommand.class, ProjectShowAllCommand.class,
            ProjectCreateCommand.class, ProjectUpdateCommand.class, ProjectDeleteCommand.class,
            TaskCreateCommand.class, TaskUpdateCommand.class, TaskDeleteCommand.class,
            ExitCommand.class, HelpCommand.class, DataSaveCommand.class, DataLoadCommand.class,
            AuthorizationRegister.class, AuthorizationLoginCommand.class,
            AuthorizationLogoutCommand.class, AuthorizationChangePassword.class};

    public Bootstrap() {
        commandList = new HashMap<>();
        scanner = new Scanner(System.in);
        final AuthorizationEndpointImplService authorizationEndpoint = new AuthorizationEndpointImplService();
        authorizationService = AuthorizationServiceImpl.getInstance(authorizationEndpoint);
        final ProjectEndpointImplService projectEndpoint = new ProjectEndpointImplService();
        projectService = ProjectServiceImpl.getInstance(projectEndpoint);
        final TaskEndpointImplService taskEndpoint = new TaskEndpointImplService();
        taskService = TaskServiceImpl.getInstance(taskEndpoint);
    }

    private void registry(Class clazz) throws IllegalAccessException, InstantiationException {
        Command command = (Command) clazz.newInstance();
        command.setServiceLocator(this);
        commandList.put(command.getName(), command);
    }

    public void registry() throws IllegalAccessException, InstantiationException {
        for (Class clazz:
             classes) {
            registry(clazz);
        }
    }

    public void run() throws NoSuchAlgorithmException, IOException, URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        registry();
        while (true) {
            System.out.println("\nEnter your command (enter \"help\" for list of commands)");
            final String input = scanner.nextLine().toLowerCase();
            Command command = commandList.get(input);
            if (command != null && (!command.isSecure() || authorizationService.isLogged())){
                command.execute();
            } else {
                System.out.println("Command not recognized. Try again, please");
            }
        }
    }




    @Override
    public ProjectService getProjectService() {
        return projectService;
    }

    @Override
    public TaskService getTaskService() {
        return taskService;
    }

    // TODO: 19.01.2019 implement methods

    @Override
    public UserService getUserService() {
        return null;
    }

    @Override
    public AuthorizationService getAuthorizationService() {
        return authorizationService;
    }

    @Override
    public Scanner getScanner() {
        return scanner;
    }

    @Override
    public Collection<Command> getCommandList() {
        return Collections.unmodifiableCollection(commandList.values());
    }
}
