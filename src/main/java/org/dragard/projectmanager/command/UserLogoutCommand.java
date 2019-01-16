package org.dragard.projectmanager.command;

public class UserLogoutCommand extends AbstractCommand{

    public UserLogoutCommand() {
        super("logout", "Logout", true);
    }

    @Override
    public void execute() {
        getServiceLocator().getAuthorizationService().logout();
        getServiceLocator().getProjectService().getElements().clear();
        getServiceLocator().getTaskService().getElements().clear();
    }
}
