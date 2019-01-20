package org.dragard.projectmanager.server.endpoint;

import org.dragard.projectmanager.server.Bootstrap;
import org.dragard.projectmanager.server.api.endpoint.AuthorizationEndpoint;
import org.dragard.projectmanager.server.entity.Response;
import org.dragard.projectmanager.server.entity.User;

import static org.dragard.projectmanager.server.util.UtilClass.*;

import javax.jws.WebService;
import java.util.Arrays;

@WebService
public class AuthorizationEndpointImpl
    implements AuthorizationEndpoint {

    private Bootstrap bootstrap;

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public AuthorizationEndpointImpl() {
    }

    public AuthorizationEndpointImpl(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public Response login(String login, byte[] password) {
        System.out.println(String.format("Logged in %s %s", login, Arrays.toString(password)));
        Response response = new Response();
        User user;
        try {
            user = getBootstrap().getAuthorizationService().login(login, password);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(serializeExceptionToByteArray(e));
            return response;
        }
        response.setMessage(String.format("Logged in %s %s", login, new String(password)));
        response.setToken(createToken(user));
        return response;
    }

    @Override
    public Response logout() {
        System.out.println("Logged out");
        getBootstrap().getAuthorizationService().logout();
        // TODO: 20.01.2019 tokens black list
        Response response = new Response();
        response.setToken(null);
        response.setMessage("Logged out");
        return response;
    }
}
