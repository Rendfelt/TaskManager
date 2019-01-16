package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl
    implements UserRepository {

    private final Map<String, User> users;

    public UserRepositoryImpl() {
        this.users = new HashMap<>();
    }

    @Override
    public User getElementByLogin(String login) {
        return users.get(login);
    }

    @Override
    public void create(User user) {
        users.put(user.getLogin(), user);
    }

    @Override
    public void update(User user) {
        create(user);
    }

    @Override
    public Map getElements() {
        return users;
    }

    @Override
    public void delete(String login) {
        users.remove(login);
    }
}
