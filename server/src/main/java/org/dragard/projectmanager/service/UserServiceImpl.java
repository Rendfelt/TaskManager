package org.dragard.projectmanager.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.util.HibernateUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Getter
@Setter
@NoArgsConstructor
@ApplicationScoped
public class UserServiceImpl extends AbstractEntityService<User>
    implements UserService {

    @Inject
    private UserRepository userRepository;

    /*public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }*/

    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }

    @Override
    public User create(String login, String password) throws Exception {
        if (login == null || login.isEmpty()){
            throw new Exception("Login is empty");
        }
        if (password == null || password.isEmpty()){
            throw new Exception("Password is empty");
        }
        if (getElementByLogin(login) != null){
            throw new Exception("Login is occupied");
        }

        final EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        final User user = User.newInstance(login, password);
        final User resultUser = getRepository().merge(user, entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();

        return resultUser;
    }

    @Override
    public User getElementByLogin(String login) throws Exception {
        if (login == null || login.isEmpty()){
            return null;
        }

        final EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        final User resultUser = getRepository().getElementByLogin(login, entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();

        return resultUser;
    }

    @Override
    public User changePassword(String password, User user) throws Exception {
        if (password == null || password.isEmpty()){
            throw new Exception("Password is empty");
        }

        final EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        final User user1 = getRepository().getElementById(user.getId(), entityManager);
        if (user1 == null){
            throw new Exception("No user");
        }

        user1.setPassword(password);
        final User resultUser = getRepository().merge( user1, entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();
        return resultUser;
    }


}
