package org.dragard.projectmanager;

import org.dragard.projectmanager.command.*;
import org.dragard.projectmanager.repository.ProjectRepository;
import org.dragard.projectmanager.repository.TaskRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Application {

    public final static String NO_ACTIVE_PROJECT_MESSAGE = "Choose active project fist";
    public final static String PARSE_ID_FAILTURE_MESSAGE = "Enter id (number)";

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public List<AbstractCommand> getCommandList() {
        return Collections.unmodifiableList(commandList);
    }

    private final List<AbstractCommand> commandList;

    private final Scanner scanner;

    static{
        
    }

    public Application() {
        this.projectRepository = new ProjectRepository(this);
        this.taskRepository = new TaskRepository(this);
        commandList = new ArrayList<>();
        commandList.add(new CreateProjectCommand(this));
        commandList.add(new ChooseActiveProjectCommand(this));
        commandList.add(new UpdateProjectCommand(this));
        commandList.add(new DeleteProjectCommand(this));
        commandList.add(new CreateTaskCommand(this));
        commandList.add(new ChooseActiveTaskCommand(this));
        commandList.add(new UpdateTaskCommand(this));
        commandList.add(new DeleteTaskCommand(this));
        commandList.add(new ExitCommand(this));
        commandList.add(new HelpCommand(this));
        scanner = new Scanner(System.in);
        initializeTestData();
    }

    private void initializeTestData(){
        projectRepository.create(41, "ProjectName1", "Description1");
        projectRepository.create(42, "ProjectName2", "Description2");
        projectRepository.create(43, "ProjectName3", "Description3");
        projectRepository.setActive(41);
        taskRepository.create(41, "TaskName1", "Description1");
        taskRepository.create(42, "TaskName2", "Description2");
        projectRepository.setActive(43);
        taskRepository.create(43, "TaskName3", "Description3");
        taskRepository.create(44, "TaskName5", "Description6");
    }

    public Scanner getScanner() {
        return scanner;
    }

    public static void main(String[] args) {
        Application app = new Application();
        while (true) {
            System.out.println("\nEnter your command (enter \"help\" for list of commands)");
            System.out.printf("%-20s%s\n", "Active project:", app.getProjectRepository().getActive() == null ? "none" : app.getProjectRepository().getActive().toString());
            System.out.printf("%-20s%s\n", "Active task:", app.getTaskRepository().getActive() == null ? "none" : app.getTaskRepository().getActive().toString());
            final String input = app.scanner.nextLine();
            boolean executedFlag = false;
            for (AbstractCommand command :
                    app.commandList) {
                if (command.getName().equals(input.toLowerCase())){
                    command.execute();
                    executedFlag = true;
                    break;
                }
            }
            if (!executedFlag){
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
}
