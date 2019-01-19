package org.dragard.projectmanager.server.api.repository;

import org.dragard.projectmanager.server.entity.User;

public interface UserRepository extends Repository<User>{

    User getElementByLogin(String login);
}
