package org.dragard.projectmanager.service;

import javafx.beans.NamedArg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.annotation.NotEmpty;
import org.dragard.projectmanager.api.annotation.NullAndEmptyChecker;
import org.dragard.projectmanager.api.annotation.Preferred;
import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.util.HibernateUtils;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Getter
@Setter
@Transactional
@NoArgsConstructor
@ApplicationScoped
@NullAndEmptyChecker
public class UserServiceImpl extends AbstractEntityService<User>
    implements UserService {

    @Inject @Preferred
    private UserRepository userRepository;

    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }

    @Override
    public User create(
            @NamedArg (value = "login") @Nullable @NotEmpty String login,
            @NamedArg (value = "password") @Nullable @NotEmpty String password
    ) throws Exception {
        if (getElementByLogin(login) != null){
            throw new RuntimeException("Login is occupied");
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
    public User getElementByLogin(
            @NamedArg (value = "login") @Nullable @NotEmpty String login
    ) throws Exception {
        final EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        final User resultUser = getRepository().getElementByLogin(login, entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();

        return resultUser;
    }

    @Override
    public User changePassword(
            @NamedArg (value = "password") @Nullable @NotEmpty String password,
            @NamedArg (value = "user") @Nullable User user
    ) throws Exception {
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
