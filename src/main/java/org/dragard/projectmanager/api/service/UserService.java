package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.exception.AbstractTaskManagerExceptionImpl;

public interface UserService extends EntityService<User>{

    void create(String name, byte[] password) throws AbstractTaskManagerExceptionImpl;

    User getElementByLogin(String login);

    void changePassword(byte[] password, User user) throws AbstractTaskManagerExceptionImpl;
}
