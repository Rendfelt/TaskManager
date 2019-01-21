package org.dragard.projectmanager.endpoint;

import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.api.endpoint.AuthorizationEndpoint;
import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.exception.TaskManagerException;
import org.dragard.projectmanager.util.UtilClass;

import javax.jws.WebService;

@WebService
public class AuthorizationEndpointImpl extends AbstractEndpoint
    implements AuthorizationEndpoint {

    public AuthorizationEndpointImpl() {
    }

    public AuthorizationEndpointImpl(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public Response changePassword(String oldPassword, String password, String token){
        Response response = new Response();
        try {
            final User user = getBootstrap().getAuthorizationService().getActiveUser();
            UtilClass.checkToken(token, response);
            if (!user.getPassword().equals(oldPassword)){
                response.setMessage("Bad password");
                return response;
            }
            getBootstrap().getUserService().changePassword(password, getBootstrap().getAuthorizationService().getActiveUser());
            response.setMessage("Password changed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }

        return response;
    }

    @Override
    public Response registerUser(String login, String password){
        Response response = new Response();
        User user;
        try {
            user = getBootstrap().getUserService().create(login, password);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        response.setMessage(String.format("User created  id: %s  login: %s password: %s", user.getId(), user.getName(), user.getPassword()));

        return response;
    }

    @Override
    public Response login(String login, String password) {
        Response response = new Response();
        try {
            final User user = getBootstrap().getAuthorizationService().login(login, password);
            response.setMessage(String.format("Logged in %s %s", user.getName(), user.getPassword()));
            response.setToken(UtilClass.createToken(user));
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        return response;
    }

    @Override
    public Response logout(String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            System.out.println("Logged out");
            getBootstrap().getAuthorizationService().logout();
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
