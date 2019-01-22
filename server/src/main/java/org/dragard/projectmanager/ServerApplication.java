package org.dragard.projectmanager;

import org.dragard.projectmanager.command.*;

public class ServerApplication {

    private final static Class[] classes = {TaskShowAllCommand.class, ProjectShowAllCommand.class, ProjectShowAllCommand.class,
            ProjectCreateCommand.class, ProjectUpdateCommand.class, ProjectDeleteCommand.class,
            TaskCreateCommand.class, TaskUpdateCommand.class, TaskDeleteCommand.class,
            ExitCommand.class, HelpCommand.class, DataSaveCommand.class, DataLoadCommand.class,
            UserChangePasswordCommand.class, UserCreateCommand.class, AuthorizationLoginCommand.class,
            AuthorizationLogoutCommand.class, UserDeleteCurrentCommand.class, UserShowAllCommand.class,
            DataXMLSaveCommand.class, DataJSONSaveCommand.class, DataXMLLoadCommand.class,
            DataJSONLoadCommand.class};

    public static void main(String[] args) throws Exception {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.registry(classes);
        bootstrap.run();

    }

}
