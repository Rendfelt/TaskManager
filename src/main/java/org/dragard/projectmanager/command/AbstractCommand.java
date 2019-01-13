package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Application;

public abstract class AbstractCommand implements CommandInterface {

    private final String name;
    private final String description;
    private final Application application;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    protected Application getApplication() {
        return application;
    }

    public AbstractCommand(String name, String description, Application application) {
        this.name = name;
        this.description = description;
        this.application = application;
    }

    @Override
    public String toString() {
        return getName();
    }
}
