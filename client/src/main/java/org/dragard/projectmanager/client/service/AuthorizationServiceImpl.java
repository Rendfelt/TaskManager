package org.dragard.projectmanager.client.service;

import org.dragard.projectmanager.client.api.ServiceLocator;
import org.dragard.projectmanager.client.api.service.AuthorizationService;
import org.dragard.projectmanager.client.endpoint.AuthorizationEndpointImplService;
import org.dragard.projectmanager.client.endpoint.Response;

import static org.dragard.projectmanager.client.util.UtilClass.checkResponse;

public class AuthorizationServiceImpl
    implements AuthorizationService {

    private final ServiceLocator serviceLocator;
    private String token;

    public AuthorizationServiceImpl(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
        token = null;
    }

    @Override
    public Response login(String login, byte[] password) throws Exception {
        AuthorizationEndpointImplService authorizationEndpointService = new AuthorizationEndpointImplService();
        Response response = authorizationEndpointService.getAuthorizationEndpointImplPort().login(login, password);
        checkResponse(response);
        token = response.getToken();
        return response;
    }

    @Override
    public Response logout(String token) throws Exception {
        this.token = null;
        AuthorizationEndpointImplService authorizationEndpointService = new AuthorizationEndpointImplService();
        Response response = authorizationEndpointService.getAuthorizationEndpointImplPort().logout(token);
        checkResponse(response);
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
