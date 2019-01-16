package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.User;

public interface AuthorizationService {

    void login(String login, byte[] password) throws Exception;

    void logout();

    User getActiveUser();

    boolean isLogged();
}
