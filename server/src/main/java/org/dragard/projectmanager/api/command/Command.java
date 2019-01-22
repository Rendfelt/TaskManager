package org.dragard.projectmanager.api.command;

import org.dragard.projectmanager.api.ServiceLocator;

public interface Command {

    void execute() throws Exception;

    String getName();

    String getDescription();

    void setServiceLocator(ServiceLocator serviceLocator);

    boolean isSecure();
}
