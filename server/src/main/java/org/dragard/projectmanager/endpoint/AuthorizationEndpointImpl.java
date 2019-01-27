package org.dragard.projectmanager.endpoint;

import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.api.endpoint.AuthorizationEndpoint;
import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.exception.TaskManagerException;
import org.dragard.projectmanager.util.UtilClass;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class AuthorizationEndpointImpl
    implements AuthorizationEndpoint {
    
    private Bootstrap bootstrap;

    public AuthorizationEndpointImpl() {
    }

    public AuthorizationEndpointImpl(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    @WebMethod(operationName = "changePassword")
    public Response changePassword(
            @WebParam(name = "oldPassword") String oldPassword,
            @WebParam(name = "password") String password,
            @WebParam(name = "token") String token
    ){
        Response response = new Response();
        try {
            final User user = bootstrap.getAuthorizationService().getActiveUser();
            UtilClass.checkToken(token, response);
            if (!user.getPassword().equals(oldPassword)){
                response.setMessage("Bad password");
                return response;
            }
            bootstrap.getUserService().changePassword(password, bootstrap.getAuthorizationService().getActiveUser());
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
            @WebParam(name = "login") String login,
            @WebParam(name = "password") String password
    ){
        Response response = new Response();
        User user;
        try {
            user = bootstrap.getUserService().create(login, password);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        response.setMessage(String.format("User created  id: %s  login: %s password: %s", user.getId(), user.getName(), user.getPassword()));

        return response;
    }

    @Override
    public Response login(
            @WebParam(name = "login") String login,
            @WebParam(name = "password") String password
    ) throws Exception {
        Response response = new Response();
        /*try {*/
            final User user = bootstrap.getAuthorizationService().login(login, password);
            response.setMessage(String.format("Logged in %s %s", user.getName(), user.getPassword()));
            response.setToken(UtilClass.createToken(user));
        /*} catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }*/
        return response;
    }

    @Override
    public Response logout(
            @WebParam(name = "token") String token
    ) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            System.out.println("Logged out");
            bootstrap.getAuthorizationService().logout();
            // TODO: 20.01.2019 tokens black list
            response.setToken(null);
            response.setMessage("Logged out");
        } catch (TaskManagerException e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
