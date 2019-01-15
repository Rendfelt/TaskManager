package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.command.Command;

import java.util.Collection;

public class HelpCommand extends AbstractCommand{

    public HelpCommand() {
        super("help", "Show all commands with description");
    }

    @Override
    public void execute() {
        Collection<Command> commandList = getServiceLocator().getCommandList().values();
        for (Command command :
               commandList ) {
            System.out.printf("%-20s%s\n", command.getName(), command.getDescription());
        }
    }
}
