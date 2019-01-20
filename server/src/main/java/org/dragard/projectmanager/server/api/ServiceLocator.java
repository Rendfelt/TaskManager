package org.dragard.projectmanager.server.api;

import org.dragard.projectmanager.server.api.service.*;

import javax.jws.WebService;
import java.util.Scanner;

public interface ServiceLocator {

    ProjectService getProjectService();

    TaskService getTaskService();

    UserService getUserService();

    AuthorizationService getAuthorizationService();

    Scanner getScanner();

    DomainService getDomainService();
}
