package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.User;

public interface UserService extends EntityService<User>{

    User create(String login, String password);

    User getElementByLogin(String login);

    User changePassword(String password, String userId);
}
