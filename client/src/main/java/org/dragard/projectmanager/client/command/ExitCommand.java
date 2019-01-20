package org.dragard.projectmanager.client.command;

import org.dragard.projectmanager.client.api.service.AuthorizationService;

public class ExitCommand extends AbstractCommand{

    public ExitCommand() {
        super("exit", "Close application", false);
    }

    @Override
    public void execute() {
        try {
            AuthorizationService as = getServiceLocator().getAuthorizationService();
            as.logout(as.getToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
