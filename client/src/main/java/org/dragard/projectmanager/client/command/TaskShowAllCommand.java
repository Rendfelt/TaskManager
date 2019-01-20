package org.dragard.projectmanager.client.command;

import org.dragard.projectmanager.client.endpoint.Response;

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
