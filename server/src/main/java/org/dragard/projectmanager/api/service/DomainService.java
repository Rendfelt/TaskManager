package org.dragard.projectmanager.api.service;

import java.io.IOException;
import java.net.URISyntaxException;

public interface DomainService extends Service {

    void saveUserList() throws URISyntaxException, IOException;

    void loadUserList() throws URISyntaxException, IOException, ClassNotFoundException;

    void loadXML() throws URISyntaxException, IOException;

    void loadJSON() throws URISyntaxException, IOException;

    void saveSerialization() throws IOException, URISyntaxException;

    void loadSerialization() throws IOException, ClassNotFoundException, URISyntaxException;

    void saveXML() throws URISyntaxException, IOException;

    void saveJSON() throws IOException, URISyntaxException;
}
