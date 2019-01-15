package org.dragard.projectmanager;

import org.dragard.projectmanager.command.*;

public class Application {

    private final static Class[] clazzes = {ShowTasksCommand.class, ShowProjectsCommand.class, ShowProjectsCommand.class,
            CreateProjectCommand.class, UpdateProjectCommand.class, DeleteProjectCommand.class,
            CreateTaskCommand.class, UpdateTaskCommand.class, DeleteTaskCommand.class,
            ExitCommand.class, HelpCommand.class};

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.registry(clazzes);
        bootstrap.run();
    }

}
