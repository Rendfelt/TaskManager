package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.endpoint.AuthorizationEndpointImplService;
import org.dragard.projectmanager.endpoint.Response;

import org.dragard.projectmanager.util.UtilClass;

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
    public Response register(String login, String password) throws Exception {
        Response response = authorizationEndpoint.getAuthorizationEndpointImplPort().registerUser(login, password);
        UtilClass.checkResponse(response);
        return response;
    }

    @Override
    public Response changePassword(String oldPassword, String password, String token) throws Exception {
        Response response = authorizationEndpoint.getAuthorizationEndpointImplPort().changePassword(oldPassword, password, token);
        UtilClass.checkResponse(response);
        return response;
    }

    @Override
    public Response login(String login, String password) throws Exception {
        Response response = authorizationEndpoint.getAuthorizationEndpointImplPort().login(login, password);
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
