package org.dragard.projectmanager.command;

import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.endpoint.Response;

public class AuthorizationLogoutCommand extends AbstractCommand{

    public AuthorizationLogoutCommand() {
        super("logout", "Logout", true);
    }

    @Override
    public void execute() {
        final AuthorizationService as = getServiceLocator().getAuthorizationService();
        final Response response = as.logout(as.getToken());
        if (response.getMessage() != null){
            System.out.println(response.getMessage());
        }
    }
}
