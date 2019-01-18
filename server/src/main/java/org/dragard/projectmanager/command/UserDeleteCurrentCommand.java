package org.dragard.projectmanager.command;

import org.dragard.projectmanager.service.DomainServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class UserDeleteCurrentCommand extends AbstractCommand{

    public UserDeleteCurrentCommand() {
        super("delete_user", "Delete this user and logout", true);
    }

    @Override
    public void execute() throws URISyntaxException, IOException {
        getServiceLocator().getUserService().delete(getServiceLocator().getAuthorizationService().getActiveUser().getId());
        new DomainServiceImpl(getServiceLocator()).saveUserList();
        getServiceLocator().getAuthorizationService().logout();
    }
}
