package org.dragard.projectmanager.client.service;

import org.dragard.projectmanager.client.api.service.AuthorizationService;
import org.dragard.projectmanager.client.endpoint.AuthorizationEndpointImplService;
import org.dragard.projectmanager.client.endpoint.Response;

import org.dragard.projectmanager.client.util.UtilClass;

import java.nio.charset.StandardCharsets;

public class AuthorizationServiceImpl
    implements AuthorizationService {

    private String token;

    public AuthorizationEndpointImplService getAuthorizationEndpoint() {
        return authorizationEndpoint;
    }

    private final AuthorizationEndpointImplService authorizationEndpoint;

    public AuthorizationServiceImpl(AuthorizationEndpointImplService authorizationEndpoint) {
        token = null;
        this.authorizationEndpoint = authorizationEndpoint;
    }

    @Override
    public Response login(String login, byte[] password) throws Exception {
        Response response = authorizationEndpoint.getAuthorizationEndpointImplPort().login(login, new String(password, StandardCharsets.UTF_8));
        UtilClass.checkResponse(response);
        token = response.getToken();
        return response;
    }

    @Override
    public Response logout(String token) throws Exception {
        Response response = authorizationEndpoint.getAuthorizationEndpointImplPort().logout(token);
        this.token = null;
        UtilClass.checkResponse(response);
        return response;
    }

    @Override
    public boolean isLogged() {
        return token != null;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }
}
