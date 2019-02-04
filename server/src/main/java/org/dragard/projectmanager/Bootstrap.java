package org.dragard.projectmanager;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.command.Command;
import org.dragard.projectmanager.api.endpoint.AuthorizationEndpoint;
import org.dragard.projectmanager.api.endpoint.ProjectEndpoint;
import org.dragard.projectmanager.api.endpoint.TaskEndpoint;
import org.dragard.projectmanager.api.endpoint.service.AuthorizationEndpointService;
import org.dragard.projectmanager.api.endpoint.service.ProjectEndpointService;
import org.dragard.projectmanager.api.endpoint.service.TaskEndpointService;
import org.dragard.projectmanager.api.service.*;
import org.dragard.projectmanager.command.ExitCommand;
import org.dragard.projectmanager.command.HelpCommand;
import org.dragard.projectmanager.endpoint.AuthorizationEndpointImpl;
import org.dragard.projectmanager.endpoint.ProjectEndpointImpl;
import org.dragard.projectmanager.endpoint.TaskEndpointImpl;
import org.dragard.projectmanager.util.UtilClass;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;
import java.util.*;

@ApplicationScoped
public class Bootstrap
    implements ServiceLocator {

    @Inject
    private  ProjectService projectService;

    @Inject
    private  TaskService taskService;

    @Inject
    private  UserService userService;

    @Inject
    private  AuthorizationService authorizationService;

    @Inject
    private AuthorizationEndpointService authorizationEndpointService;

    @Inject
    private ProjectEndpointService projectEndpointService;

    @Inject
    private TaskEndpointService taskEndpointService;

    private  Map<String, Command> commandList;

    private  Scanner scanner;

    private final static Class[] classes = {
            ExitCommand.class, HelpCommand.class
    };

    public Bootstrap() {
        commandList = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    private void initializeTestData(){
        try {
            userService.create("test", UtilClass.getPassword("test"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            userService.create("root", UtilClass.getPassword("root"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void registry(Class clazz) throws IllegalAccessException, InstantiationException {
        Command command = (Command) clazz.newInstance();
        command.setServiceLocator(this);
        commandList.put(command.getName(), command);
    }

    public void registry() throws IllegalAccessException, InstantiationException {
        for (Class clazz : classes) {
            registry(clazz);
        }
    }

    private void initialize(){
        try {
            registry();
        } catch (Exception e) {
            e.printStackTrace();
        }

        AuthorizationEndpoint authorizationEndpoint = new AuthorizationEndpointImpl(authorizationEndpointService);
        Endpoint.publish("http://localhost:9090/task-manager/auth", authorizationEndpoint);
        System.out.println("http://localhost:9090/task-manager/auth");

        ProjectEndpoint projectEndpoint = new ProjectEndpointImpl(projectEndpointService);
        Endpoint.publish("http://localhost:9090/task-manager/project", projectEndpoint);
        System.out.println("http://localhost:9090/task-manager/project");

        TaskEndpoint taskEndpoint = new TaskEndpointImpl(taskEndpointService);
        Endpoint.publish("http://localhost:9090/task-manager/task", taskEndpoint);
        System.out.println("http://localhost:9090/task-manager/task");
        initializeTestData();
    }

    @Inject
    private Event<String> stringEvent;

    public void run() {
        initialize();
        stringEvent.fire("");
    }

    private void handleConsoleEvent(@Observes String message){
        System.out.println("\nEnter your command (enter \"help\" for list of commands)");
        final String input = scanner.nextLine().toLowerCase();
        Command command = commandList.get(input);
        if (command != null && (!command.isSecure() || authorizationService.isLogged())){
            try {
                command.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Command not recognized. Try again, please");
        }
        stringEvent.fire("");
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public String getNewLine() {
        return scanner.nextLine();
    }

    @Override
    public Collection<Command> getCommandList() {
        return Collections.unmodifiableCollection(commandList.values());
    }

    @Override
    public ProjectService getProjectService() {
        return projectService;
    }

    @Override
    public TaskService getTaskService() {
        return taskService;
    }

    @Override
    public AuthorizationService getAuthorizationService() {
        return authorizationService;
    }
}
