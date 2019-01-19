package org.dragard.projectmanager.server;

import org.dragard.projectmanager.server.command.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public class Application {

    private final static Class[] classes = {TaskShowAllCommand.class, ProjectShowAllCommand.class, ProjectShowAllCommand.class,
            ProjectCreateCommand.class, ProjectUpdateCommand.class, ProjectDeleteCommand.class,
            TaskCreateCommand.class, TaskUpdateCommand.class, TaskDeleteCommand.class,
            ExitCommand.class, HelpCommand.class, DataSaveCommand.class, DataLoadCommand.class,
            UserChangePasswordCommand.class, UserCreateCommand.class, UserLoginCommand.class,
            UserLogoutCommand.class, UserDeleteCurrentCommand.class, UserShowAllCommand.class,
            DataXMLSaveCommand.class, DataJSONSaveCommand.class, DataXMLLoadCommand.class,
            DataJSONLoadCommand.class};

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchAlgorithmException, IOException, URISyntaxException, ClassNotFoundException {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.registry(classes);
        bootstrap.run();

    }

}
