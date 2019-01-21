package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.entity.User;

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
