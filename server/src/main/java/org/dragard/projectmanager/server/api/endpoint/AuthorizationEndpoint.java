package org.dragard.projectmanager.server.api.endpoint;

import javax.jws.WebService;

@WebService
public interface AuthorizationEndpoint extends Endpoint{

    String login(String login, byte[] password);

    String logout();
}
