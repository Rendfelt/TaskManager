package org.dragard.projectmanager.command;

public class ExitCommand extends AbstractCommand{

    public ExitCommand() {
        super("exit", "Close application");
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
