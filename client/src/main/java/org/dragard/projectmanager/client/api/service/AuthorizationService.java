package org.dragard.projectmanager.client.api.service;

public interface AuthorizationService extends Service{

    String login(String login, byte[] password);

    String logout();
}
