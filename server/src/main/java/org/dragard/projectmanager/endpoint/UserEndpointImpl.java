package org.dragard.projectmanager.endpoint;

import org.dragard.projectmanager.api.endpoint.UserEndpoint;
import org.dragard.projectmanager.entity.Response;

import javax.jws.WebService;

@WebService
public class UserEndpointImpl
    implements UserEndpoint {

    public Response create(String login, String password){
        final Response response = new Response();
        System.out.println(login + " - " + password);
        return response;
    }
}
