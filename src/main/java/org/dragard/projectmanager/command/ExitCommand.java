package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Application;

public class ExitCommand extends AbstractCommand{

    public ExitCommand(Application application) {
        super("exit", "Close application", application);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
