package org.dragard.projectmanager.entity;

public abstract class AbstractEntity {

    private final long id;
    private final String name;
    private final String description;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AbstractEntity(long id, String name, String description) {
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
