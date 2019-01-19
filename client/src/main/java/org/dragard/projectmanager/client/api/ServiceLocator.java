package org.dragard.projectmanager.client.api;

import org.dragard.projectmanager.client.api.service.AuthorizationService;
import org.dragard.projectmanager.client.api.service.ProjectService;
import org.dragard.projectmanager.client.api.service.TaskService;
import org.dragard.projectmanager.client.api.service.UserService;

import java.util.Scanner;

public interface ServiceLocator {

    ProjectService getProjectService();

    TaskService getTaskService();

    UserService getUserService();

    AuthorizationService getAuthorizationService();

    Scanner getScanner();
}
