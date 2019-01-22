package org.dragard.projectmanager.command;

import java.util.Scanner;

public class ProjectDeleteCommand extends AbstractCommand{

    public ProjectDeleteCommand() {
        super("delete_project", "Delete active project", true);
    }

    @Override
    public void execute() throws Exception {
        Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Enter project id:");
        final String projectId = scanner.nextLine();
        try {
            getServiceLocator().getProjectService().delete(projectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getServiceLocator().getTaskService().deleteTasksByProjectId(projectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
