package org.dragard.projectmanager.server.endpoint;

import org.dragard.projectmanager.server.Bootstrap;
import org.dragard.projectmanager.server.api.endpoint.AuthorizationEndpoint;
import org.dragard.projectmanager.server.entity.Response;
import org.dragard.projectmanager.server.entity.User;

import org.dragard.projectmanager.server.exception.TaskManagerException;
import org.dragard.projectmanager.server.util.UtilClass;

import javax.jws.WebService;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@WebService
public class AuthorizationEndpointImpl extends AbstractEndpoint
    implements AuthorizationEndpoint {

    public AuthorizationEndpointImpl() {
    }

    public AuthorizationEndpointImpl(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public Response login(String login, String password) {
        System.out.println(String.format("Logged in %s %s", login, Arrays.toString(password.getBytes(StandardCharsets.UTF_8))));
        User u = getBootstrap().getUserService().getElementByLogin(login);
        System.out.println(String.format("Logged in %s %s", u.getName(), Arrays.toString(u.getPassword())));
        System.out.println(String.format("Logged in %s %s", u.getName(),
                Arrays.toString(new String(u.getPassword()).getBytes(StandardCharsets.UTF_8))));
        Response response = new Response();
        User user;
        try {
            user = getBootstrap().getAuthorizationService().login(login, password.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        response.setMessage(String.format("Logged in %s %s", login, password));
        response.setToken(UtilClass.createToken(user));
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
