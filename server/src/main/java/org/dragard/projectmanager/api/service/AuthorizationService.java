package org.dragard.projectmanager.api.service;

import javafx.beans.NamedArg;
import org.dragard.projectmanager.entity.User;

import javax.inject.Named;
import java.io.IOException;

public interface AuthorizationService extends Service{

    String login(
            @NamedArg(value = "login") String login,
            @NamedArg(value = "password") String password
    ) throws Exception;

    void logout();

    User getActiveUser(String token);

    boolean isLogged();

    String checkToken(String token);
}
