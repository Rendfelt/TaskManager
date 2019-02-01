package org.dragard.projectmanager.endpoint.service;

import javafx.beans.NamedArg;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.endpoint.service.AuthorizationEndpointService;
import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.exception.TaskManagerException;
import org.dragard.projectmanager.util.UtilClass;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizationEndpointServiceImpl
    implements AuthorizationEndpointService {

    @Getter
    @Setter
    private Bootstrap serviceLocator;

    public AuthorizationEndpointServiceImpl(Bootstrap serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public Response changePassword(
            @NamedArg(value = "oldPassword") String oldPassword,
            @NamedArg(value = "password") String password,
            @NamedArg(value = "token") String token
    ) {
        Response response = new Response();
        try {
            String activeUserId = serviceLocator.getAuthorizationService().checkToken(token);
            User activeUser = serviceLocator.getUserService().getElementById(activeUserId);
            if (!activeUser.getPassword().equals(oldPassword)){
                response.setMessage("Bad password");
                return response;
            }
            serviceLocator.getUserService().changePassword(password, activeUser);
            response.setMessage("Password changed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }

        return response;
    }

    @Override
    public Response registerUser(
            @NamedArg(value = "login") String login,
            @NamedArg(value = "password") String password
    ) {
        Response response = new Response();
        try {
            User user = serviceLocator.getUserService().create(login, password);
            response.setMessage(String.format("User created  id: %s  login: %s password: %s", user.getId(), user.getLogin(), user.getPassword()));
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
    }

    @Override
    public Response login(
            @NamedArg(value = "login") String login,
            @NamedArg(value = "password") String password
    ) throws Exception {
        Response response = new Response();
        try {
//        response.setMessage(String.format("Logged in %s %s", user.getLogin(), user.getPassword()));
        response.setToken(serviceLocator.getAuthorizationService().login(login, password));
        System.out.println(response.getToken());
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        return response;
    }

    @Override
    public Response logout(
            @NamedArg(value = "token") String token
    ) {
        Response response = new Response();
        try {
            serviceLocator.getAuthorizationService().checkToken(token);
            System.out.println("Logged out");
            serviceLocator.getAuthorizationService().logout();
            // TODO: 20.01.2019 tokens black list
            response.setToken(null);
            response.setMessage("Logged out");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

    }
}
