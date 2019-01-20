package org.dragard.projectmanager.client.command;

import org.dragard.projectmanager.client.api.service.AuthorizationService;
import org.dragard.projectmanager.client.endpoint.Response;

public class AuthorizationLogoutCommand extends AbstractCommand{

    public AuthorizationLogoutCommand() {
        super("logout", "Logout", true);
    }

    @Override
    public void execute() {
        try {
            final AuthorizationService as = getServiceLocator().getAuthorizationService();
            final Response response = as.logout(as.getToken());
            if (response.getMessage() != null){
                System.out.println(response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
