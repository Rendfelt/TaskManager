package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.User;

public interface AuthorizationService extends Service{

    User login(String login, String password) throws Exception;

    void logout();

    User getActiveUser();

    boolean isLogged();
}
