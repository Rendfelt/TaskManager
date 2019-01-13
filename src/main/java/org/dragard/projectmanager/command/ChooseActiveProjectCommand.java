package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Application;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.repository.ProjectRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class ChooseActiveProjectCommand extends AbstractCommand{

    public ChooseActiveProjectCommand(Application application) {
        super("choose_project", "Choose active project, is critical for some commands", application);
    }

    @Override
    public void execute() {
        Scanner scanner = getApplication().getScanner();
        ProjectRepository repository = getApplication().getProjectRepository();
        repository.printElements();
        System.out.println("Enter project id to make it active:");
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
        getApplication().getTaskRepository().clearActive();
    }
}
