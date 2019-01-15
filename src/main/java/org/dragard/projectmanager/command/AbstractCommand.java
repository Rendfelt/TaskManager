package org.dragard.projectmanager.command;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.command.Command;

public abstract class AbstractCommand implements Command {

    private final String name;
    private final String description;
    private ServiceLocator serviceLocator;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return getName();
    }
}
