package org.dragard.projectmanager.command;

import org.dragard.projectmanager.api.command.Command;

import java.util.Collection;

public class HelpCommand extends AbstractCommand{

    public HelpCommand() {
        super("help", "Show all commands with description", false);
    }

    @Override
    public void execute() {
        final Collection<Command> commandList = getServiceLocator().getCommandList();
        for (Command command : commandList ) {
            if (!getServiceLocator().getAuthorizationService().isLogged() && command.isSecure()){
                continue;
            }
            System.out.printf("%-20s%s\n", command.getName(), command.getDescription());
        }
    }
}
