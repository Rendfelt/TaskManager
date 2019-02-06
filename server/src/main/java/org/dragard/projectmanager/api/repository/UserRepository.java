package org.dragard.projectmanager.api.repository;

import org.dragard.projectmanager.entity.User;

import javax.persistence.EntityManager;

public interface UserRepository extends IRepository<User> {

    User getElementByLogin(String login, EntityManager entityManager) throws Exception;
}
