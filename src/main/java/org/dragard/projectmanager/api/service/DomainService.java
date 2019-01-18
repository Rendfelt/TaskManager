package org.dragard.projectmanager.api.service;

import java.io.IOException;
import java.net.URISyntaxException;

public interface DomainService extends Service {

    void saveUserList() throws URISyntaxException, IOException;

    void loadUserList() throws URISyntaxException, IOException, ClassNotFoundException;

    void save() throws IOException, URISyntaxException;

    void load() throws IOException, ClassNotFoundException, URISyntaxException;

}
