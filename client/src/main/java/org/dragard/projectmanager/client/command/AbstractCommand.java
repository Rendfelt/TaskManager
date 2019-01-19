package org.dragard.projectmanager.client.command;

import org.dragard.projectmanager.client.api.ServiceLocator;
import org.dragard.projectmanager.client.api.command.Command;
import java.util.Objects;

public abstract class AbstractCommand implements Command {

    private final String name;
    private final String description;
    private ServiceLocator serviceLocator;
    private final boolean isSecure;

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

    public AbstractCommand(String name, String description, boolean isSecure) {
        this.name = name;
        this.description = description;
        this.isSecure = isSecure;
    }

    public boolean isSecure() {
        return isSecure;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractCommand command = (AbstractCommand) o;
        return Objects.equals(name, command.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
