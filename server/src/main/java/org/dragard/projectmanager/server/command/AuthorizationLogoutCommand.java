package org.dragard.projectmanager.server.command;

public class AuthorizationLogoutCommand extends AbstractCommand{

    public AuthorizationLogoutCommand() {
        super("logout", "Logout", true);
    }

    @Override
    public void execute() {
        getServiceLocator().getAuthorizationService().logout();
        getServiceLocator().getProjectService().clearElements();
        getServiceLocator().getTaskService().clearElements();
    }
}
