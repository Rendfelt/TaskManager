package org.dragard.projectmanager;

import org.dragard.projectmanager.command.*;

public class Application {

    private final static Class[] clazzes = {TaskShowAllCommand.class, ProjectShowAllCommand.class, ProjectShowAllCommand.class,
            ProjectCreateCommand.class, ProjectUpdateCommand.class, ProjectDeleteCommand.class,
            TaskCreateCommand.class, TaskUpdateCommand.class, TaskDeleteCommand.class,
            ExitCommand.class, HelpCommand.class, DataSaveCommand.class, DataLoadCommand.class};

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.registry(clazzes);
        bootstrap.run();
    }

}
