package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Application;
import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.api.ServiceLocator;

public class ExitCommand extends AbstractCommand{

    public ExitCommand(ServiceLocator serviceLocator) {
        super("exit", "Close application", serviceLocator);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
