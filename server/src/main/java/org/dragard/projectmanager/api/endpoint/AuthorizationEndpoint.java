package org.dragard.projectmanager.api.endpoint;

import javafx.beans.NamedArg;
import org.dragard.projectmanager.api.endpoint.service.EndpointService;
import org.dragard.projectmanager.entity.Response;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface AuthorizationEndpoint extends Endpoint{

    @WebMethod
    Response changePassword(
            @NamedArg(value = "oldPassword") String oldPassword,
            @NamedArg(value = "password") String password,
            @NamedArg(value = "token") String token
    );

    @WebMethod
    Response registerUser(
            @NamedArg(value = "login") String login,
            @NamedArg(value = "password") String password
    );

    @WebMethod
    Response login(
            @NamedArg(value = "login") String login,
            @NamedArg(value = "password") String password
    ) throws Exception;

    @WebMethod
    Response logout(
            @NamedArg(value = "token") String token
    );
}
