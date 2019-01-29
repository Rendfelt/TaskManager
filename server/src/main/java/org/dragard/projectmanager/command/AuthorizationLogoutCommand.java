package org.dragard.projectmanager.command;

public class AuthorizationLogoutCommand extends AbstractCommand{

    public AuthorizationLogoutCommand() {
        super("logout", "Logout", true);
    }

    @Override
    public void execute() throws Exception {
        getServiceLocator().getAuthorizationService().logout();
    }
}
