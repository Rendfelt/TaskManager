package org.dragard.projectmanager.client.api.command;

import org.dragard.projectmanager.client.api.ServiceLocator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public interface Command {

    void execute() throws NoSuchAlgorithmException, URISyntaxException, IOException, ClassNotFoundException;

    String getName();

    String getDescription();

    void setServiceLocator(ServiceLocator serviceLocator);

    boolean isSecure();
}