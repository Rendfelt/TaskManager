package org.dragard.projectmanager.client.api.service;

import org.dragard.projectmanager.client.endpoint.Response;

public interface AuthorizationService extends Service{

    Response login(String login, byte[] password) throws Exception;

    Response logout(String token) throws Exception;

    boolean isLogged();

    String getToken();

    void setToken(String token);
}
