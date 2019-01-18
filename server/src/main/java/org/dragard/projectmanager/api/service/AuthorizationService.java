package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.exception.TaskManagerException;

public interface AuthorizationService extends Service{

    void login(String login, byte[] password) throws TaskManagerException;

    void logout();

    User getActiveUser();

    boolean isLogged();
}
