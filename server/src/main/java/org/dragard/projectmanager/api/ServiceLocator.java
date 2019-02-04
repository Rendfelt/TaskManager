package org.dragard.projectmanager.api;

import org.dragard.projectmanager.api.command.Command;
import org.dragard.projectmanager.api.service.*;

import java.util.Collection;

public interface ServiceLocator {

    Collection<Command> getCommandList();

    ProjectService getProjectService();

    TaskService getTaskService();

    UserService getUserService();

    AuthorizationService getAuthorizationService();

    String getNewLine();
}
