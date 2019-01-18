package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.api.command.Command;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
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
