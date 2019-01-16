package org.dragard.projectmanager.api.repository;

import org.dragard.projectmanager.entity.User;

import java.util.Map;

public interface UserRepository{

    User getElementByLogin(final String login);

    void create(User element);

    void update(User element);

    Map<String, User> getElements();

    void delete(String login);
}
