package org.dragard.projectmanager.api.command;

import org.dragard.projectmanager.api.ServiceLocator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public interface Command {

    void execute() throws NoSuchAlgorithmException, URISyntaxException, IOException;

    String getName();

    String getDescription();

    void setServiceLocator(ServiceLocator serviceLocator);

    boolean isSecure();
}
