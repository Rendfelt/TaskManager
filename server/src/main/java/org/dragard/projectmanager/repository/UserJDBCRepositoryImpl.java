package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.entity.User;

import java.util.Collection;

public class UserJDBCRepositoryImpl extends AbstractJDBCRepository<User>
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

    @Override
    public Collection<User> getElements() {
        return super.getElements();
    }

    @Override
    public User getElementById(String id) {
        return super.getElementById(id);
    }

    @Override
    public void clearElements() {
        super.clearElements();
    }

    @Override
    public User delete(String id) {
        return super.delete(id);
    }

    @Override
    public User merge(User element) {
        return super.merge(element);
    }
}
