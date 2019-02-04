package org.dragard.projectmanager.api;

import org.dragard.projectmanager.api.command.Command;
import org.dragard.projectmanager.api.service.*;

import java.util.Collection;
import java.util.Scanner;

public interface ServiceLocator {

    ProjectService getProjectService();

    TaskService getTaskService();

    InterfaceService getInterfaceService();

    AuthorizationService getAuthorizationService();

    Collection<Command> getCommandList();
}
