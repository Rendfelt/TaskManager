package org.dragard.projectmanager;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.command.Command;
import org.dragard.projectmanager.api.endpoint.AuthorizationEndpoint;
import org.dragard.projectmanager.api.endpoint.service.AuthorizationEndpointService;
import org.dragard.projectmanager.api.endpoint.service.ProjectEndpointService;
import org.dragard.projectmanager.api.endpoint.service.TaskEndpointService;
import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.*;
import org.dragard.projectmanager.endpoint.AuthorizationEndpointImpl;
import org.dragard.projectmanager.endpoint.ProjectEndpointImpl;
import org.dragard.projectmanager.endpoint.TaskEndpointImpl;
import org.dragard.projectmanager.endpoint.service.AuthorizationEndpointServiceImpl;
import org.dragard.projectmanager.endpoint.service.ProjectEndpointServiceImpl;
import org.dragard.projectmanager.endpoint.service.TaskEndpointServiceImpl;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.repository.ProjectHibernateRepository;
import org.dragard.projectmanager.repository.TaskHibernateRepository;
import org.dragard.projectmanager.repository.UserHibernateRepository;
import org.dragard.projectmanager.service.AuthorizationServiceImpl;
import org.dragard.projectmanager.service.ProjectServiceImpl;
import org.dragard.projectmanager.service.TaskServiceImpl;
import org.dragard.projectmanager.service.UserServiceImpl;
import org.dragard.projectmanager.util.UtilClass;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.util.*;

public class Bootstrap implements ServiceLocator {

    private  ProjectService projectService;
    private  TaskService taskService;
    private  UserService userService;
    private  AuthorizationService authorizationService;
    private  Map<String, Command> commandList;
    private  Scanner scanner;

    public Bootstrap() throws Exception {
        final TaskRepository taskRepository = new TaskHibernateRepository();
        final UserRepository userRepository = new UserHibernateRepository();
        final ProjectRepository projectRepository = new ProjectHibernateRepository();
        taskService = new TaskServiceImpl(taskRepository, userRepository, projectRepository);
        projectService = new ProjectServiceImpl(projectRepository, taskRepository, userRepository);
        userService = new UserServiceImpl(userRepository);
        authorizationService = new AuthorizationServiceImpl(userService);
        commandList = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    private void initializeTestData(){
        try {
            User test = userService.create("test", UtilClass.getPassword("test"));
        } catch (Exception e) {

        }
        try {
            User root = userService.create("root", UtilClass.getPassword("root"));
        } catch (Exception e) {

        }
    }


    private void registry(Class clazz) throws IllegalAccessException, InstantiationException {
        Command command = (Command) clazz.newInstance();
        command.setServiceLocator(this);
        commandList.put(command.getName(), command);
    }

    public void registry(Class[] clazzes) throws IllegalAccessException, InstantiationException {
        for (Class clazz:
             clazzes) {
            registry(clazz);
        }
    }

    public void run() {
        AuthorizationEndpointServiceImpl authorizationEndpointService = new AuthorizationEndpointServiceImpl(this);
        AuthorizationEndpoint authorizationEndpoint = new AuthorizationEndpointImpl(authorizationEndpointService);
        Endpoint.publish("http://localhost:9090/task-manager/auth", authorizationEndpoint);
        ProjectEndpointServiceImpl projectEndpointService = new ProjectEndpointServiceImpl(this);
        ProjectEndpointImpl projectEndpoint = new ProjectEndpointImpl(projectEndpointService);
        Endpoint.publish("http://localhost:9090/task-manager/project", projectEndpoint);
        TaskEndpointServiceImpl taskEndpointService = new TaskEndpointServiceImpl(this);
        TaskEndpointImpl taskEndpoint = new TaskEndpointImpl(taskEndpointService);
        Endpoint.publish("http://localhost:9090/task-manager/task", taskEndpoint);
        initializeTestData();

        while (true) {
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
        }
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public Scanner getScanner() {
        return scanner;
    }

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

    public AuthorizationService getAuthorizationService() {
        return authorizationService;
    }
}
