package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.entity.User;
import java.util.Map;
import java.util.UUID;

public class UserServiceImpl
    implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public void create(String login, byte[] password) throws Exception {
        if (login == null || login.isEmpty()){
            throw new Exception("Login is empty");
        }
        if (password == null || password.length == 0){
            throw new Exception("Password is empty");
        }
        if (getElementByLogin(login) != null){
            throw new Exception("Login is occupied");
        }
        repository.create(new User(UUID.randomUUID().toString(), login, password));
    }

    @Override
    public User getElementByLogin(String login) {
        return repository.getElementByLogin(login);
    }

    @Override
    public void changePassword(byte[] password, User user) {
        repository.update( new User(user.getId(), user.getLogin(), password));
    }

    @Override
    public Map<String, User> getElements() {
        return repository.getElements();
    }

    @Override
    public void delete(String login) {
        repository.delete(login);
    }
}
