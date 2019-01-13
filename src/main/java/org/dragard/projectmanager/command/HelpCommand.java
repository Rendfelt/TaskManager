package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Application;

import java.util.List;

public class HelpCommand extends AbstractCommand{

    public HelpCommand(Application application) {
        super("help", "Show all commands with description", application);
    }

    @Override
    public void execute() {
        List<AbstractCommand> acList = getApplication().getCommandList();
        for (AbstractCommand ac :
               acList ) {
            System.out.printf("%-20s%s\n", ac.getName(), ac.getDescription());
        }
    }
}
