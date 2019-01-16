package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.entity.User;

import java.util.Arrays;

public class AuthorizationServiceImpl
    implements AuthorizationService {

    private User activeUser;
    private final UserService userService;

    public AuthorizationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void login(String login, byte[] password) throws Exception {
        User user = userService.getElementByLogin(login);
        if (user == null || !Arrays.equals(user.getPassword(), password)){
            throw new Exception("Bad login or password");
        }
        activeUser = user;
    }

    @Override
    public void logout() {
        activeUser = null;
    }

    @Override
    public User getActiveUser() {
        return activeUser;
    }

    @Override
    public boolean isLogged() {
        return activeUser != null;
    }
}
