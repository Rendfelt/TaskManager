package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.exception.AbstractTaskManagerExceptionImpl;

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
    public void create(String name, byte[] password) throws AbstractTaskManagerExceptionImpl {
        if (name == null || name.isEmpty()){
            throw new AbstractTaskManagerExceptionImpl("Login is empty");
        }
        if (password == null || password.length == 0){
            throw new AbstractTaskManagerExceptionImpl("Password is empty");
        }
        if (getElementByLogin(name) != null){
            throw new AbstractTaskManagerExceptionImpl("Login is occupied");
        }
        getRepository().merge(new User(UUID.randomUUID().toString(), name, password));
    }

    @Override
    public User getElementByLogin(String login) {
        if (login == null || login.isEmpty()){
            return null;
        }
        return getRepository().getElementByLogin(login);
    }

    @Override
    public void changePassword(byte[] password, User user) throws AbstractTaskManagerExceptionImpl {
        if (password == null || password.length == 0){
            throw new AbstractTaskManagerExceptionImpl("Password is empty");
        }
        getRepository().merge( new User(user.getId(), user.getName(), password));
    }

}
