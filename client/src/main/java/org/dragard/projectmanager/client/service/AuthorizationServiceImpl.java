package org.dragard.projectmanager.client.service;

import org.dragard.projectmanager.client.api.ServiceLocator;
import org.dragard.projectmanager.client.api.service.AuthorizationService;
import org.dragard.projectmanager.client.endpoint.AuthorizationEndpointImplService;
import org.dragard.projectmanager.client.endpoint.Response;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;

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
        System.out.println(String.format("Logged in %s %s", login, Arrays.toString(password)));
        AuthorizationEndpointImplService authorizationEndpointService = new AuthorizationEndpointImplService();
        Response response = authorizationEndpointService.getAuthorizationEndpointImplPort().login(login, password);
        checkResponse(response);
        token = response.getToken();
        return response;
    }

    private void checkResponse(Response response) throws Exception{
        if (response.getException() == null){
            return;
        }
        Exception exception = (Exception) new ObjectInputStream(new ByteArrayInputStream(response.getException())).readObject();
        if (response.getException() != null){
            throw exception;
        }
    }

    @Override
    public Response logout(String token) throws Exception {
        this.token = null;
        AuthorizationEndpointImplService authorizationEndpointService = new AuthorizationEndpointImplService();
        Response response = authorizationEndpointService.getAuthorizationEndpointImplPort().logout();
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
