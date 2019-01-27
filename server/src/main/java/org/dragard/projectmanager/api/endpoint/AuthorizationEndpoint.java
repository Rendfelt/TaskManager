package org.dragard.projectmanager.api.endpoint;

import org.dragard.projectmanager.entity.Response;

import javax.jws.WebService;

@WebService
public interface AuthorizationEndpoint extends Endpoint{

    Response changePassword(String oldPassword, String password, String token);

    Response registerUser(String login, String password);

    Response login(String login, String password) throws Exception;

    Response logout(String token);
}
