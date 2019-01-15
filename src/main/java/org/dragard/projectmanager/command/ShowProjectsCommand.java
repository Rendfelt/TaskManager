package org.dragard.projectmanager.command;

import org.dragard.projectmanager.entity.Project;

public class ShowProjectsCommand extends AbstractCommand {

    public ShowProjectsCommand() {
        super("show_projects", "Show list of projects");
    }

    @Override
    public void execute() {
        System.out.printf("\n%-40s%-40s%-100s\n", "uid", "name", "description");
        for (Project project :
                getServiceLocator().getProjectService().getElements().values()) {
            System.out.printf("%-40s%-40s%-100s\n", project.getId(),project.getName(), project.getDescription());
        }
    }
}
