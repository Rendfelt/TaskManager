package org.dragard.projectmanager.command;

import org.dragard.projectmanager.domain.DomainImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class UserDeleteCurrentCommand extends AbstractCommand{

    public UserDeleteCurrentCommand() {
        super("delete_user", "Delete this user and logout", true);
    }

    @Override
    public void execute() throws URISyntaxException, IOException {
        Scanner scanner = getServiceLocator().getScanner();
        getServiceLocator().getUserService().delete(getServiceLocator().getAuthorizationService().getActiveUser().getLogin());
        new DomainImpl(getServiceLocator()).saveUserList();
        getServiceLocator().getAuthorizationService().logout();
    }
}
