package org.dragard.projectmanager.command;

import org.dragard.projectmanager.entity.Task;

public class TaskShowAllCommand extends AbstractCommand {

    public TaskShowAllCommand() {
        super("show_tasks", "Show list of tasks");
    }

    @Override
    public void execute() {
        System.out.printf("\n%-40s%-40s%-40s%-100s\n", "uid", "project id", "name", "description");
        for (Task task :
                getServiceLocator().getTaskService().getElements().values()) {
            System.out.printf("%-40s%-40s%-40s%-100s\n", task.getId(), task.getProjectId(),task.getName(), task.getDescription());
        }
    }
}
