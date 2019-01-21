package org.dragard.projectmanager.api.service;

public interface UserService extends EntityService{

    void create(String name, byte[] password);

    void changePassword(byte[] password);
}
