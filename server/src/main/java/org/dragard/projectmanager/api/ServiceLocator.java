package org.dragard.projectmanager.api;

import org.dragard.projectmanager.api.service.*;

import java.util.Scanner;

public interface ServiceLocator {

    ProjectService getProjectService();

    TaskService getTaskService();

    UserService getUserService();

    AuthorizationService getAuthorizationService();

    Scanner getScanner();
}
