package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.endpoint.Response;

public interface AuthorizationService extends Service{

    Response register(String login, String password);

    Response changePassword(String oldPassword, String password, String token);

    Response login(String login, String password);

    Response logout(String token);

    boolean isLogged();

    String getToken();

    void setToken(String token);
}
