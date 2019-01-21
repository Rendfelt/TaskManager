package org.dragard.projectmanager.command;

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
