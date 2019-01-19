package org.dragard.projectmanager.server.command;

public class ExitCommand extends AbstractCommand{

    public ExitCommand() {
        super("exit", "Close application", false);
    }

    @Override
    public void execute() {
        getServiceLocator().getAuthorizationService().logout();
        System.exit(0);
    }
}
