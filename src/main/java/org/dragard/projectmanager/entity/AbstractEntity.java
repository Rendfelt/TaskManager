package org.dragard.projectmanager.entity;

import java.io.Serializable;

public abstract class AbstractEntity
    implements Serializable {

    private final String id;
    private final String name;
    private final String description;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    protected AbstractEntity(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'';
    }
}
