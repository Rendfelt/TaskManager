package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.entity.User;

import java.util.UUID;

public class UserServiceImpl extends AbstractEntityService<User>
    implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    protected UserRepository getRepository() {
        return repository;
    }

    @Override
    public User create(String name, String password) throws Exception {
        if (name == null || name.isEmpty()){
            throw new Exception("Login is empty");
        }
        if (password == null || password.isEmpty()){
            throw new Exception("Password is empty");
        }
        if (getElementByLogin(name) != null){
            throw new Exception("Login is occupied");
        }
        return getRepository().merge(new User(UUID.randomUUID().toString(), name, password));
    }

    @Override
    public User getElementByLogin(String login) throws Exception {
        if (login == null || login.isEmpty()){
            return null;
        }
        return getRepository().getElementByLogin(login);
    }

    @Override
    public void changePassword(String password, User user) throws Exception {
        if (password == null || password.isEmpty()){
            throw new Exception("Password is empty");
        }
        getRepository().merge( new User(user.getId(), user.getName(), password));
    }

}
