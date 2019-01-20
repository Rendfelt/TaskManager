package org.dragard.projectmanager.client.command;

import org.dragard.projectmanager.client.endpoint.Response;

public class ProjectShowAllCommand extends AbstractCommand {

    public ProjectShowAllCommand() {
        super("show_projects", "Show list of projects", true);
    }

    @Override
    public void execute() {
        try {
            Response response = getServiceLocator().getProjectService().getView(
                    getServiceLocator().getAuthorizationService().getToken());
            System.out.println(response.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
