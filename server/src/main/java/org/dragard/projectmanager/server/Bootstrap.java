package org.dragard.projectmanager.server;

import org.dragard.projectmanager.server.api.ServiceLocator;
import org.dragard.projectmanager.server.api.command.Command;
import org.dragard.projectmanager.server.api.endpoint.AuthorizationEndpoint;
import org.dragard.projectmanager.server.api.endpoint.ProjectEndpoint;
import org.dragard.projectmanager.server.api.endpoint.TaskEndpoint;
import org.dragard.projectmanager.server.api.repository.ProjectRepository;
import org.dragard.projectmanager.server.api.repository.TaskRepository;
import org.dragard.projectmanager.server.api.repository.UserRepository;
import org.dragard.projectmanager.server.api.service.*;
import org.dragard.projectmanager.server.endpoint.AuthorizationEndpointImpl;
import org.dragard.projectmanager.server.endpoint.ProjectEndpointImpl;
import org.dragard.projectmanager.server.endpoint.TaskEndpointImpl;
import org.dragard.projectmanager.server.service.DomainServiceImpl;
import org.dragard.projectmanager.server.entity.Project;
import org.dragard.projectmanager.server.repository.ProjectRepositoryImpl;
import org.dragard.projectmanager.server.repository.TaskRepositoryImpl;
import org.dragard.projectmanager.server.repository.UserRepositoryImpl;
import org.dragard.projectmanager.server.service.AuthorizationServiceImpl;
import org.dragard.projectmanager.server.service.ProjectServiceImpl;
import org.dragard.projectmanager.server.service.TaskServiceImpl;
import org.dragard.projectmanager.server.service.UserServiceImpl;
import org.dragard.projectmanager.server.util.UtilClass;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Bootstrap implements ServiceLocator {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final UserService userService;
    private final DomainService domainService;
    private final AuthorizationService authorizationService;
    private final Map<String, Command> commandList;
    private final Scanner scanner;

    public Bootstrap() {
        final ProjectRepository projectRepository = new ProjectRepositoryImpl();
        final TaskRepository taskRepository = new TaskRepositoryImpl();
        final UserRepository userRepository = new UserRepositoryImpl();
        taskService = new TaskServiceImpl(taskRepository);
        projectService = new ProjectServiceImpl(projectRepository);
        userService = new UserServiceImpl(userRepository);
        authorizationService = new AuthorizationServiceImpl(userService);
        commandList = new HashMap<>();
        scanner = new Scanner(System.in);
        domainService = new DomainServiceImpl(this);
    }

    private void initializeTestData(){
        try {

            projectService.create("ProjectName1", "Description1", "ea4f8798-4868-4565-a243-d674d318d71a");
            projectService.create("ProjectName2", "Description2", "ea4f8798-4868-4565-a243-d674d318d71a");
            projectService.create("ProjectName3", "Description3", "ea4f8798-4868-4565-a243-d674d318d71a");
            projectService.create("ProjectName4", "Description4", "ea4f8798-4868-4565-a243-d674d318d71a");
            Iterator<Project> iterator = projectService.getElements().iterator();
            final Project project1 = iterator.next();
            final Project project2 = iterator.next();
            taskService.create("TaskName1", "TaskDescription1", project1.getId(), "ea4f8798-4868-4565-a243-d674d318d71a");
            taskService.create("TaskName2", "TaskDescription2", project1.getId(), "ea4f8798-4868-4565-a243-d674d318d71a");
            taskService.create("TaskName3", "TaskDescription3", project1.getId(), "ea4f8798-4868-4565-a243-d674d318d71a");
            taskService.create("TaskName4", "TaskDescription4", project2.getId(), "ea4f8798-4868-4565-a243-d674d318d71a");
            taskService.create("TaskName5", "TaskDescription5", project2.getId(), "ea4f8798-4868-4565-a243-d674d318d71a");
            userService.create("test", UtilClass.getPassword("test"));
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

    public void registry(Class[] clazzes) throws IllegalAccessException, InstantiationException {
        for (Class clazz:
             clazzes) {
            registry(clazz);
        }
    }

    public void run() throws NoSuchAlgorithmException, IOException, URISyntaxException, ClassNotFoundException {
        AuthorizationEndpoint authorizationEndpoint = new AuthorizationEndpointImpl(this);
        Endpoint.publish("http://localhost:9090/task-manager/auth", authorizationEndpoint);
        ProjectEndpoint projectEndpoint = new ProjectEndpointImpl(this);
        Endpoint.publish("http://localhost:9090/task-manager/project", projectEndpoint);
        TaskEndpoint taskEndpoint = new TaskEndpointImpl(this);
        Endpoint.publish("http://localhost:9090/task-manager/task", taskEndpoint);
        initializeTestData();
        try {
            domainService.loadUserList();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    @Override
    public DomainService getDomainService() {
        return domainService;
    }

    public AuthorizationService getAuthorizationService() {
        return authorizationService;
    }
}
