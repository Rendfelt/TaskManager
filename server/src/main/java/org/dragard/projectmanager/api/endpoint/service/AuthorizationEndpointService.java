package org.dragard.projectmanager.api.endpoint.service;

import javafx.beans.NamedArg;
import org.dragard.projectmanager.entity.Response;

import javax.jws.WebService;

public interface AuthorizationEndpointService extends EndpointService {

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
    );

    Response logout(
            @NamedArg(value = "token") String token
    );
}
