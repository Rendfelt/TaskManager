package org.dragard.projectmanager.client.command;

import org.dragard.projectmanager.server.Bootstrap;
import org.dragard.projectmanager.server.api.command.Command;

import java.util.Collection;

public class HelpCommand extends AbstractCommand{

    public HelpCommand() {
        super("help", "Show all commands with description", false);
    }

    @Override
    public void execute() {
        final Bootstrap bootstrap = (Bootstrap) getServiceLocator();
        final Collection<Command> commandList = bootstrap.getCommandList();
        for (Command command : commandList ) {
            if (!getServiceLocator().getAuthorizationService().isLogged() && command.isSecure()){
                continue;
            }
            System.out.printf("%-20s%s\n", command.getName(), command.getDescription());
        }
    }
}
