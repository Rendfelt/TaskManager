package org.dragard.projectmanager.command;

import org.dragard.projectmanager.entity.Project;

public class ProjectShowAllCommand extends AbstractCommand {

    public ProjectShowAllCommand() {
        super("show_projects", "Show list of projects", true);
    }

    @Override
    public void execute() throws Exception {
        System.out.printf("\n%-40s%-40s%-100s\n", "uid", "name", "description");
        try {
            for (Project project :
                    getServiceLocator().getProjectService().getElements()) {
                System.out.printf("%-40s%-40s%-100s\n", project.getId(),project.getName(), project.getDescription());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
