package org.dragard.projectmanager.service;

import javafx.beans.NamedArg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.annotation.NotEmpty;
import org.dragard.projectmanager.api.annotation.NullAndEmptyChecker;
import org.dragard.projectmanager.repository.UserRepository;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.entity.User;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Getter
@Setter
@Transactional
@NoArgsConstructor
@Component
@NullAndEmptyChecker
public class UserServiceImpl extends AbstractEntityService<User>
    implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    protected UserRepository getRepository() {
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
        return getRepository().save(user);
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
        final User user1 = getRepository().findById(userId).orElse(null);
        if (user1 == null){
            throw new RuntimeException("No user");
        }
        user1.setPassword(password);
        return getRepository().save(user1);
    }


}
