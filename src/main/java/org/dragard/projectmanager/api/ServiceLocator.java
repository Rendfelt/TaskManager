package org.dragard.projectmanager.api;

import org.dragard.projectmanager.api.command.Command;
import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.api.service.UserService;

import java.util.Map;
import java.util.Scanner;

public interface ServiceLocator {

    ProjectService getProjectService();

    TaskService getTaskService();

    UserService getUserService();

    AuthorizationService getAuthorizationService();

    Scanner getScanner();

    Map<String, Command> getCommandList();
}
