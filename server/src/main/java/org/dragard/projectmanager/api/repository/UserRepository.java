package org.dragard.projectmanager.api.repository;

import org.dragard.projectmanager.entity.User;

public interface UserRepository extends Repository<User>{

    User getElementByLogin(String login) throws Exception;
}
