package org.dragard.projectmanager.service;

import javafx.beans.NamedArg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.annotation.NotEmpty;
import org.dragard.projectmanager.api.annotation.NullAndEmptyChecker;
import org.dragard.projectmanager.api.annotation.Preferred;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.repository.UserDSRepository;
import org.jetbrains.annotations.Nullable;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Getter
@Setter
@Transactional
@NoArgsConstructor
@ApplicationScoped
@NullAndEmptyChecker
public class UserServiceImpl extends AbstractEntityService<User>
    implements UserService {

    @Inject @Preferred
    private UserDSRepository userRepository;

    @Override
    protected UserDSRepository getRepository() {
        return userRepository;
    }

    @Override
    public User create(
            @NamedArg (value = "login") @Nullable @NotEmpty String login,
            @NamedArg (value = "password") @Nullable @NotEmpty String password
    ){
        if (getElementByLogin(login) != null){
            throw new RuntimeException("Login is occupied");
        }
        final User user = User.newInstance(login, password);
        return getRepository().merge(user);
    }

    @Override
    public User getElementByLogin(
            @NamedArg (value = "login") @Nullable @NotEmpty String login
    ){
        final User resultUser;
        try {
            resultUser = getRepository().findByLogin(login);
        } catch (Exception e) {
            return null;
        }
        return resultUser;
    }

    @Override
    public User changePassword(
            @NamedArg (value = "password") @Nullable @NotEmpty String password,
            @NamedArg (value = "userId") @Nullable String userId
    ) {
        final User user1 = getRepository().findBy(userId);
        if (user1 == null){
            throw new RuntimeException("No user");
        }
        user1.setPassword(password);
        return getRepository().merge(user1);
    }


}
