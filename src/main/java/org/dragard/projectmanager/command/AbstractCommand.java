package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.command.Command;

public abstract class AbstractCommand implements Command {

    private String name;
    private String description;
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

    public AbstractCommand() {
    }

    public AbstractCommand(String name, String description, ServiceLocator serviceLocator) {
        this.name = name;
        this.description = description;
        this.serviceLocator = serviceLocator;
    }

    @Override
    public String toString() {
        return getName();
    }
}
