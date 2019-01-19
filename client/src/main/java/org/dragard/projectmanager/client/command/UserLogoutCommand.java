package org.dragard.projectmanager.client.command;

public class UserLogoutCommand extends AbstractCommand{

    public UserLogoutCommand() {
        super("logout", "Logout", true);
    }

    @Override
    public void execute() {
        getServiceLocator().getAuthorizationService().logout();
        getServiceLocator().getProjectService().clearElements();
        getServiceLocator().getTaskService().clearElements();
    }
}
