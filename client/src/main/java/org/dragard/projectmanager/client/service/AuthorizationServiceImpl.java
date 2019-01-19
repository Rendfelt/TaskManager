package org.dragard.projectmanager.client.service;

import org.dragard.projectmanager.client.api.ServiceLocator;
import org.dragard.projectmanager.client.api.service.AuthorizationService;

public class AuthorizationServiceImpl
    implements AuthorizationService {


    private boolean isLogged;
    private final ServiceLocator serviceLocator;

    public AuthorizationServiceImpl(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
        isLogged = false;
    }

    @Override
    public String login(String login, byte[] password) {
        //TODO:
        isLogged = true;
        return null;
    }

    @Override
    public String logout() {
        isLogged = false;
        //TODO:
        return null;
    }

    @Override
    public boolean isLogged() {
        return isLogged;
    }
}
