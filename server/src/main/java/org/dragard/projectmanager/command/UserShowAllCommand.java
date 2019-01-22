package org.dragard.projectmanager.command;

import org.dragard.projectmanager.entity.User;

import java.util.Collection;

public class UserShowAllCommand extends AbstractCommand{

    public UserShowAllCommand() {
        super("show_users", "Show all users", false);
    }

    @Override
    public void execute() throws Exception {
        final Collection<User> users;
        users = getServiceLocator().getUserService().getElements();
        for (User user :
                users) {
            System.out.println(user.getName());
        }
    }
}
