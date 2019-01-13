package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Application;
import org.dragard.projectmanager.repository.ProjectRepository;
import org.dragard.projectmanager.repository.TaskRepository;

import java.util.Scanner;

public class ChooseActiveTaskCommand extends AbstractCommand{

    public ChooseActiveTaskCommand(Application application) {
        super("choose_task", "Choose active task, is critical for some commands (set project of the task active)", application);
    }

    @Override
    public void execute() {
        Scanner scanner = getApplication().getScanner();
        TaskRepository repository = getApplication().getTaskRepository();
        ProjectRepository projectRepository = getApplication().getProjectRepository();
        repository.printElements();
        System.out.println("Enter task id to make it active:");
        long id;
        while (true) {
            try {
                id = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(Application.PARSE_ID_FAILTURE_MESSAGE);
                continue;
            }
            break;
        }
        repository.setActive(id);
        if (repository.getActive() != null) {
            projectRepository.setActive(repository.getProjectIdByTaskId(id));
        }
    }
}
