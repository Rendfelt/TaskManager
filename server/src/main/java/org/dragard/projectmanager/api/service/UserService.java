package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.User;

public interface UserService extends EntityService<User>{

    User create(String login, String password) throws Exception;

    User getElementByLogin(String login) throws Exception;

    User changePassword(String password, User user) throws Exception;
}
