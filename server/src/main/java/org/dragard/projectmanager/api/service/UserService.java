package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.User;

public interface UserService extends EntityService<User>{

    User create(String name, String password) throws Exception;

    User getElementByLogin(String login) throws Exception;

    void changePassword(String password, User user) throws Exception;
}
