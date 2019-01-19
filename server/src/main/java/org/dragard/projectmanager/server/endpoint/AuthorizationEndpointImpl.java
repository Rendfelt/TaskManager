package org.dragard.projectmanager.server.endpoint;

import org.dragard.projectmanager.server.api.endpoint.AuthorizationEndpoint;

import javax.jws.WebService;

@WebService
public class AuthorizationEndpointImpl extends AbstractEndpoint
    implements AuthorizationEndpoint {

    @Override
    public String login(String login, byte[] password) {
        return "Logged in";
    }

    @Override
    public String logout() {
        return "Logged out";
    }
}
