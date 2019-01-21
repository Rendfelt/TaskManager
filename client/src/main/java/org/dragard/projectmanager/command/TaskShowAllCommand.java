package org.dragard.projectmanager.command;

import org.dragard.projectmanager.endpoint.Response;

public class TaskShowAllCommand extends AbstractCommand {

    public TaskShowAllCommand() {
        super("show_tasks", "Show list of tasks", true);
    }

    @Override
    public void execute() {
        try {
            Response response = getServiceLocator().getTaskService().getView(
                    getServiceLocator().getAuthorizationService().getToken());
            System.out.println(response.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
