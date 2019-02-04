package org.dragard.projectmanager.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.annotation.ResponceHandle;
import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.api.service.Service;
import org.dragard.projectmanager.endpoint.AuthorizationEndpointImplService;
import org.dragard.projectmanager.endpoint.Exception_Exception;
import org.dragard.projectmanager.endpoint.Response;

import org.dragard.projectmanager.util.UtilClass;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ResponceHandle
@ApplicationScoped
@NoArgsConstructor
public class AuthorizationServiceImpl
    implements AuthorizationService {

    private String token;

    @Getter
    @Setter
    private AuthorizationEndpointImplService authorizationEndpoint;

    protected AuthorizationServiceImpl(AuthorizationEndpointImplService authorizationEndpoint) {
        token = null;
        this.authorizationEndpoint = authorizationEndpoint;
    }

   /* public static AuthorizationService getInstance(AuthorizationEndpointImplService authorizationEndpoint){
        return (AuthorizationService) UtilClass.getServiceProxy(AuthorizationService.class, new AuthorizationServiceImpl(authorizationEndpoint));
    }*/

    @Override
    public Response register(String login, String password) {
        return authorizationEndpoint.getAuthorizationEndpointImplPort().registerUser(login, password);
    }

    @Override
    public Response changePassword(String oldPassword, String password, String token) {
        return authorizationEndpoint.getAuthorizationEndpointImplPort().changePassword(oldPassword, password, token);
    }

    @Override
    public Response login(String login, String password) {
        Response response = authorizationEndpoint.getAuthorizationEndpointImplPort().login(login, password);
        if (response.getToken() != null){
            token = response.getToken();
        }
        return response;
    }

    @Override
    public Response logout(String token) {
        Response response = authorizationEndpoint.getAuthorizationEndpointImplPort().logout(token);
        this.token = null;
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
