package org.dragard.projectmanager.api.domain;

import org.dragard.projectmanager.entity.User;

import java.io.IOException;
import java.net.URISyntaxException;

public interface Domain {

    void saveUserList() throws URISyntaxException, IOException;

    void loadUserList() throws URISyntaxException, IOException, ClassNotFoundException;

    void save() throws IOException, URISyntaxException;

    void load() throws IOException, ClassNotFoundException, URISyntaxException;

}
