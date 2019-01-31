package org.dragard.projectmanager.command;

public class UserDeleteCurrentCommand extends AbstractCommand{

    public UserDeleteCurrentCommand() {
        super("delete_user", "Delete this user and logout", true);
    }

    @Override
    public void execute() throws Exception {
        try {
            getServiceLocator().getUserService().delete(getServiceLocator().getAuthorizationService().getActiveUser().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        getServiceLocator().getAuthorizationService().logout();
    }
}
