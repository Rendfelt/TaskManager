package org.dragard.projectmanager.api.endpoint;

import javafx.beans.NamedArg;
import org.dragard.projectmanager.api.endpoint.service.EndpointService;
import org.dragard.projectmanager.entity.Response;

import javax.jws.WebService;

@WebService
public interface AuthorizationEndpoint extends Endpoint{

    Response changePassword(
            @NamedArg(value = "oldPassword") String oldPassword,
            @NamedArg(value = "password") String password,
            @NamedArg(value = "token") String token
    );

    Response registerUser(
            @NamedArg(value = "login") String login,
            @NamedArg(value = "password") String password
    );

    Response login(
            @NamedArg(value = "login") String login,
            @NamedArg(value = "password") String password
    ) throws Exception;

    Response logout(
            @NamedArg(value = "token") String token
    );
}
