package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.entity.User;

import javax.naming.OperationNotSupportedException;
import java.util.Collection;

public class UserRepositoryImpl extends AbstractRepository<User>
    implements UserRepository {

    @Override
    public User getElementByLogin(String login) throws Exception {
        try {
            for (User user : getElements()) {
                if (login.equals(user.getName())){
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<User> getElementsByUserId(String id) throws Exception {
        throw new OperationNotSupportedException();
    }
}
