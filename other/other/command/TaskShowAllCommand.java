package org.dragard.projectmanager.command;

import org.dragard.projectmanager.entity.Task;

public class TaskShowAllCommand extends AbstractCommand {

    public TaskShowAllCommand() {
        super("show_tasks", "Show list of tasks", true);
    }

    @Override
    public void execute() throws Exception {
        System.out.printf("\n%-40s%-40s%-40s%-100s\n", "uid", "project id", "name", "description");
        for (Task task :
                getServiceLocator().getTaskService().getElementsByUserId(getServiceLocator().getAuthorizationService().getActiveUser().getId())) {
            System.out.printf("%-40s%-40s%-40s%-100s\n", task.getId(), task.getProject().getId(),task.getName(), task.getDescription());
        }
    }
}
