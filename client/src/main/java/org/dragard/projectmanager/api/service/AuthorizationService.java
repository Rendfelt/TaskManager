package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.endpoint.Response;

public interface AuthorizationService extends Service{

    Response register(String login, String password) throws Exception;

    Response changePassword(String oldPassword, String password, String token) throws Exception;

    Response login(String login, String password) throws Exception;

    Response logout(String token) throws Exception;

    boolean isLogged();

    String getToken();

    void setToken(String token);
}
