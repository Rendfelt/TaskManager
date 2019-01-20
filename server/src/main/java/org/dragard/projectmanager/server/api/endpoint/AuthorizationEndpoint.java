package org.dragard.projectmanager.server.api.endpoint;

import org.dragard.projectmanager.server.entity.Response;

import javax.jws.WebService;

@WebService
public interface AuthorizationEndpoint extends Endpoint{

    Response login(String login, String password);

    Response logout(String token);
}
