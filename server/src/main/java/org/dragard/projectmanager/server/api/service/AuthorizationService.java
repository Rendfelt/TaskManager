package org.dragard.projectmanager.server.api.service;

import org.dragard.projectmanager.server.entity.User;
import org.dragard.projectmanager.server.exception.TaskManagerException;

public interface AuthorizationService extends Service{

    User login(String login, byte[] password) throws TaskManagerException;

    void logout();

    User getActiveUser();

    boolean isLogged();
}
