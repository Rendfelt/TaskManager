package org.dragard.projectmanager.client;

import org.dragard.projectmanager.client.api.ServiceLocator;
import org.dragard.projectmanager.client.api.command.Command;
import org.dragard.projectmanager.client.api.service.AuthorizationService;
import org.dragard.projectmanager.client.api.service.ProjectService;
import org.dragard.projectmanager.client.api.service.TaskService;
import org.dragard.projectmanager.client.api.service.UserService;
import org.dragard.projectmanager.client.command.*;
import org.dragard.projectmanager.client.endpoint.AuthorizationEndpointImplService;
import org.dragard.projectmanager.client.endpoint.ProjectEndpointImplService;
import org.dragard.projectmanager.client.service.AuthorizationServiceImpl;
import org.dragard.projectmanager.client.service.ProjectServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Bootstrap implements ServiceLocator {

    private final ProjectService projectService;
//    private final TaskService taskService;
//    private final UserService userService;
    private final AuthorizationService authorizationService;
    private final Map<String, Command> commandList;
    private final Scanner scanner;

    private final static Class[] classes = {TaskShowAllCommand.class, ProjectShowAllCommand.class, ProjectShowAllCommand.class,
            ProjectCreateCommand.class, ProjectUpdateCommand.class, ProjectDeleteCommand.class,
            TaskCreateCommand.class, TaskUpdateCommand.class, TaskDeleteCommand.class,
            ExitCommand.class, HelpCommand.class, DataSaveCommand.class, DataLoadCommand.class,
            UserChangePasswordCommand.class, UserCreateCommand.class, AuthorizationLoginCommand.class,
            AuthorizationLogoutCommand.class, UserDeleteCurrentCommand.class};

    public Bootstrap() {
        commandList = new HashMap<>();
        scanner = new Scanner(System.in);
        final AuthorizationEndpointImplService authorizationEndpointImplService = new AuthorizationEndpointImplService();
        authorizationService = new AuthorizationServiceImpl(authorizationEndpointImplService);
        final ProjectEndpointImplService projectEndpointImplService = new ProjectEndpointImplService();
        projectService = new ProjectServiceImpl(projectEndpointImplService);
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


    // TODO: 19.01.2019 implement methods

    @Override
    public ProjectService getProjectService() {
        return projectService;
    }

    @Override
    public TaskService getTaskService() {
        return null;
    }

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
