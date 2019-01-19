package org.dragard.projectmanager.server.repository;

import org.dragard.projectmanager.server.api.repository.UserRepository;
import org.dragard.projectmanager.server.entity.User;

public class UserRepositoryImpl extends AbstractRepository<User>
    implements UserRepository {

    @Override
    public User getElementByLogin(String login) {
        for (User user : getElements()) {
            if (login.equals(user.getName())){
                return user;
            }
        }
        return null;
    }

}
