package org.dragard.projectmanager.server.command;

import org.dragard.projectmanager.server.service.DomainServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;

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
