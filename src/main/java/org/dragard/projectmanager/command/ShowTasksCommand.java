package org.dragard.projectmanager.command;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.entity.Task;

public class ShowTasksCommand extends AbstractCommand {

    public ShowTasksCommand(ServiceLocator serviceLocator) {
        super("show_tasks", "Show list of tasks", serviceLocator);
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
