package org.dragard.projectmanager.command;

import org.dragard.projectmanager.endpoint.Response;

public class ProjectShowAllCommand extends AbstractCommand {

    public ProjectShowAllCommand() {
        super("show_projects", "Show list of projects", true);
    }

    @Override
    public void execute() {
        try {
            Response response = getServiceLocator().getProjectService().getView(
                    getServiceLocator().getAuthorizationService().getToken());
            if (response.getMessage() != null) {
                System.out.println(response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
