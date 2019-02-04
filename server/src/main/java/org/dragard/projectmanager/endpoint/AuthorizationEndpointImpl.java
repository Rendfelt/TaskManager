package org.dragard.projectmanager.endpoint;

import lombok.NoArgsConstructor;
import org.dragard.projectmanager.api.endpoint.AuthorizationEndpoint;
import org.dragard.projectmanager.api.endpoint.service.AuthorizationEndpointService;
import org.dragard.projectmanager.entity.Response;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@NoArgsConstructor
public class AuthorizationEndpointImpl
    implements AuthorizationEndpoint {

    private AuthorizationEndpointService authorizationEndpoint;

    public AuthorizationEndpointImpl(AuthorizationEndpointService authorizationEndpoint) {
        this.authorizationEndpoint = authorizationEndpoint;
    }

    @Override
    @WebMethod(operationName = "changePassword")
    public Response changePassword(
            @WebParam(name = "oldPassword") String oldPassword,
            @WebParam(name = "password") String password,
            @WebParam(name = "token") String token
    ){
        return authorizationEndpoint.changePassword(oldPassword, password, token);
    }

    @Override
    public Response registerUser(
            @WebParam(name = "login") String login,
            @WebParam(name = "password") String password
    ){
        return authorizationEndpoint.registerUser(login, password);
    }

    @Override
    public Response login(
            @WebParam(name = "login") String login,
            @WebParam(name = "password") String password
    ) {
        return authorizationEndpoint.login(login, password);
    }

    @Override
    public Response logout(
            @WebParam(name = "token") String token
    ) {
        return authorizationEndpoint.logout(token);
    }
}
