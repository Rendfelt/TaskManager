package org.dragard.projectmanager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.annotation.Preferred;
import org.dragard.projectmanager.api.command.Command;
import org.dragard.projectmanager.api.service.*;
import org.dragard.projectmanager.command.*;
import org.dragard.projectmanager.endpoint.AuthorizationEndpointImplService;
import org.dragard.projectmanager.endpoint.ProjectEndpointImplService;
import org.dragard.projectmanager.endpoint.TaskEndpointImplService;
import org.dragard.projectmanager.service.AuthorizationServiceImpl;
import org.dragard.projectmanager.service.ProjectServiceImpl;
import org.dragard.projectmanager.service.TaskServiceImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@ApplicationScoped
@NoArgsConstructor
public class Bootstrap implements ServiceLocator {

    @Getter
    @Inject
    private ProjectService projectService;

    @Getter
    @Inject
    private TaskService taskService;


    @Inject
    @Getter
    @Setter
    private InterfaceService interfaceService;

    @Getter
    @Setter
    @Inject
    @Preferred
    private AuthorizationService authorizationService;

    private final Map<String, Command> commandList = new HashMap<>();

    private final static Class[] classes = {TaskShowAllCommand.class, ProjectShowAllCommand.class, ProjectShowAllCommand.class,
            ProjectCreateCommand.class, ProjectUpdateCommand.class, ProjectDeleteCommand.class,
            TaskCreateCommand.class, TaskUpdateCommand.class, TaskDeleteCommand.class,
            ExitCommand.class, HelpCommand.class, DataSaveCommand.class,
            AuthorizationRegister.class, AuthorizationLoginCommand.class,
            AuthorizationLogoutCommand.class, AuthorizationChangePassword.class};

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

    public void run() throws Exception {
        registry();
        while (true) {
            System.out.println("\nEnter your command (enter \"help\" for list of commands)");
            final String input = interfaceService.getNewLine().toLowerCase();
            Command command = commandList.get(input);
            if (command != null && (!command.isSecure() || authorizationService.isLogged())){
                command.execute();
            } else {
                System.out.println("Command not recognized. Try again, please");
            }
        }
    }

    @Override
    public Collection<Command> getCommandList() {
        return Collections.unmodifiableCollection(commandList.values());
    }
}
