package org.dragard.projectmanager.command;

import org.dragard.projectmanager.endpoint.Response;

public class TaskShowAllCommand extends AbstractCommand {

    public TaskShowAllCommand() {
        super("show_tasks", "Show list of tasks", true);
    }

    @Override
    public void execute() {
        Response response = getServiceLocator().getTaskService().getView(
                getServiceLocator().getAuthorizationService().getToken());
        if (response.getMessage() != null) {
            System.out.println(response.getMessage());
        }
    }
}
