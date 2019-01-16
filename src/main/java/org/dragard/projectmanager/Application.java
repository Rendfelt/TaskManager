package org.dragard.projectmanager;

import org.dragard.projectmanager.command.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public class Application {

    private final static Class[] clazzes = {TaskShowAllCommand.class, ProjectShowAllCommand.class, ProjectShowAllCommand.class,
            ProjectCreateCommand.class, ProjectUpdateCommand.class, ProjectDeleteCommand.class,
            TaskCreateCommand.class, TaskUpdateCommand.class, TaskDeleteCommand.class,
            ExitCommand.class, HelpCommand.class, DataSaveCommand.class, DataLoadCommand.class,
            UserChangePasswordCommand.class, UserCreateCommand.class, UserLoginCommand.class,
            UserLogoutCommand.class, UserDeleteCurrentCommand.class, UserShowAllCommand.class};

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchAlgorithmException, IOException, URISyntaxException {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.registry(clazzes);
        bootstrap.run();
    }

}
