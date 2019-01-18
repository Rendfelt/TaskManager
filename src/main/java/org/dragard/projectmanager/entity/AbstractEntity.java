package org.dragard.projectmanager.entity;

import java.io.Serializable;

public abstract class AbstractEntity
    implements Serializable {

    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    protected AbstractEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'';
    }
}
