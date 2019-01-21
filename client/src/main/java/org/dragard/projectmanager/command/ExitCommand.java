package org.dragard.projectmanager.command;

import org.dragard.projectmanager.api.service.AuthorizationService;

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
