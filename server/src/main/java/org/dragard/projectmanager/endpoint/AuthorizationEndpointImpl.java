package org.dragard.projectmanager.endpoint;

import lombok.NoArgsConstructor;
import org.dragard.projectmanager.api.endpoint.AuthorizationEndpoint;
import org.dragard.projectmanager.endpoint.service.AuthorizationEndpointServiceImpl;
import org.dragard.projectmanager.entity.Response;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@NoArgsConstructor
@WebService
public class AuthorizationEndpointImpl
    implements AuthorizationEndpoint {
    
    private AuthorizationEndpointServiceImpl authorizationEndpoint;

    public AuthorizationEndpointImpl(AuthorizationEndpointServiceImpl authorizationEndpoint) {
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
    ) throws Exception {
        return authorizationEndpoint.login(login, password);
    }

    @Override
    public Response logout(
            @WebParam(name = "token") String token
    ) {
        return authorizationEndpoint.logout(token);
    }
}
