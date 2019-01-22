package org.dragard.projectmanager;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.command.Command;
import org.dragard.projectmanager.api.endpoint.AuthorizationEndpoint;
import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.*;
import org.dragard.projectmanager.endpoint.AuthorizationEndpointImpl;
import org.dragard.projectmanager.endpoint.ProjectEndpointImpl;
import org.dragard.projectmanager.endpoint.TaskEndpointImpl;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.repository.*;
import org.dragard.projectmanager.service.DomainServiceImpl;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.service.AuthorizationServiceImpl;
import org.dragard.projectmanager.service.ProjectServiceImpl;
import org.dragard.projectmanager.service.TaskServiceImpl;
import org.dragard.projectmanager.service.UserServiceImpl;
import org.dragard.projectmanager.util.UtilClass;

import javax.xml.ws.Endpoint;
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
        final TaskRepository taskRepository = new TaskJDBCRepositoryImpl();
        final UserRepository userRepository = new UserJDBCRepositoryImpl();
        final ProjectRepository projectRepository = new ProjectJDBCRepositoryImpl(taskRepository);
        taskService = new TaskServiceImpl(taskRepository);
        projectService = new ProjectServiceImpl(projectRepository, taskService);
        userService = new UserServiceImpl(userRepository);
        authorizationService = new AuthorizationServiceImpl(userService);
        commandList = new HashMap<>();
        scanner = new Scanner(System.in);
        domainService = new DomainServiceImpl(this);
    }

    private void initializeTestData(){
        try {
            User test = userService.create("test", UtilClass.getPassword("test"));
            User root = userService.create("root", UtilClass.getPassword("root"));
            projectService.create("ProjectName1", "Description1", test.getId());
            Project project1 = projectService.create("ProjectName2", "Description2", test.getId());
            projectService.create("ProjectName3", "Description3", root.getId());
            Project project2 = projectService.create("ProjectName4", "Description4", root.getId());
            taskService.create("TaskName1", "TaskDescription1", project1.getId(), project1.getUserId());
            taskService.create("TaskName2", "TaskDescription2", project1.getId(), project1.getUserId());
            taskService.create("TaskName3", "TaskDescription3", project1.getId(), project1.getUserId());
            taskService.create("TaskName4", "TaskDescription4", project2.getId(), project2.getUserId());
            taskService.create("TaskName5", "TaskDescription5", project2.getId(), project2.getUserId());
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

    public void run() throws Exception {
        AuthorizationEndpoint authorizationEndpoint = new AuthorizationEndpointImpl(this);
        Endpoint.publish("http://localhost:9090/task-manager/auth", authorizationEndpoint);
        ProjectEndpointImpl projectEndpoint = new ProjectEndpointImpl(this);
        Endpoint.publish("http://localhost:9090/task-manager/project", projectEndpoint);
        // TODO: 20.01.2019 Fix userEndpoint in future
/*        UserEndpoint userEndpoint = new UserEndpointImpl();
        Endpoint.publish("http://localhost:9090/task-manager/user", userEndpoint);*/
        TaskEndpointImpl taskEndpoint = new TaskEndpointImpl(this);
        Endpoint.publish("http://localhost:9090/task-manager/task", taskEndpoint);
//        initializeTestData();
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

    @Override
    public DomainService getDomainService() {
        return domainService;
    }

    public AuthorizationService getAuthorizationService() {
        return authorizationService;
    }
}
