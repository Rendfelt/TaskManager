package org.dragard.projectmanager.entity;

public abstract class AbstractJobEntity extends AbstractEntity{

    private final String description;

    public String getDescription() {
        return description;
    }

    protected AbstractJobEntity(String id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", description='" + description + '\'';
    }
}
