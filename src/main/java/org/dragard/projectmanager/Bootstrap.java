package org.dragard.projectmanager;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.command.Command;
import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.service.DomainServiceImpl;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.repository.ProjectRepositoryImpl;
import org.dragard.projectmanager.repository.TaskRepositoryImpl;
import org.dragard.projectmanager.repository.UserRepositoryImpl;
import org.dragard.projectmanager.service.AuthorizationServiceImpl;
import org.dragard.projectmanager.service.ProjectServiceImpl;
import org.dragard.projectmanager.service.TaskServiceImpl;
import org.dragard.projectmanager.service.UserServiceImpl;

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

    public AuthorizationService getAuthorizationService() {
        return authorizationService;
    }

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
    }

    private void initializeTestData(){
        try {
            projectService.create("ProjectName1", "Description1");
            projectService.create("ProjectName2", "Description2");
            projectService.create("ProjectName3", "Description3");
            projectService.create("ProjectName4", "Description4");
            Iterator<Project> iterator = projectService.getElements().iterator();
            final Project project1 = iterator.next();
            final Project project2 = iterator.next();
            taskService.create("TaskName1", "TaskDescription1", project1.getId());
            taskService.create("TaskName2", "TaskDescription2", project1.getId());
            taskService.create("TaskName3", "TaskDescription3", project1.getId());
            taskService.create("TaskName4", "TaskDescription4", project2.getId());
            taskService.create("TaskName5", "TaskDescription5", project2.getId());
            final byte[] testPassword = MessageDigest.getInstance("MD5").digest("test".getBytes(StandardCharsets.UTF_8));
            userService.create("test", testPassword);
            final byte[] rootPassword = MessageDigest.getInstance("MD5").digest("root".getBytes(StandardCharsets.UTF_8));
            userService.create("root", rootPassword);
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

    public void run() throws NoSuchAlgorithmException, IOException, URISyntaxException {
        initializeTestData();
        try {
            new DomainServiceImpl(this).loadUserList();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
}
