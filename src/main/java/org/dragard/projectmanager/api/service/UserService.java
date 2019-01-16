package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.User;

import java.util.Map;

public interface UserService {

    void create(String login, byte[] password) throws Exception;

    User getElementByLogin(final String login);

    void changePassword(byte[] password, User user);

    Map<String, User> getElements();

    void delete(String login);

}
