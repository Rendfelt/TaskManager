package org.dragard.projectmanager.client.api.service;

public interface UserService extends EntityService{

    void create(String name, byte[] password);

    void changePassword(byte[] password);
}
