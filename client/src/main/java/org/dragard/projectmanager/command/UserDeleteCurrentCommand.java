package org.dragard.projectmanager.command;

public class UserDeleteCurrentCommand extends AbstractCommand{

    public UserDeleteCurrentCommand() {
        super("delete_user", "Delete this user and logout", true);
    }

    @Override
    public void execute() {

    }
}
