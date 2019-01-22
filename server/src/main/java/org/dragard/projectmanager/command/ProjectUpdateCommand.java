package org.dragard.projectmanager.command;

import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;
import java.util.Scanner;

public class ProjectUpdateCommand extends AbstractCommand{

    public ProjectUpdateCommand() {
        super("update_project", "Update active project", true);
    }

    @Override
    public void execute() throws Exception {
        try {
            final Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Enter project id");
            final Project project = getServiceLocator().getProjectService().getElementById(scanner.nextLine());
            if (project == null){
                throw new NoElementWithIdException();
            }
            System.out.println("Enter project name:");
            final String name = scanner.nextLine();
            if (name.isEmpty()){
                throw new NoNameException();
            }
            System.out.println("Enter project description");
            final String description = scanner.nextLine();
            getServiceLocator().getProjectService().update(project.getId(), name, description
            );
        } catch (NoNameException | NoElementWithIdException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
