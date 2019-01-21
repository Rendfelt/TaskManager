package org.dragard.projectmanager.entity;

public abstract class AbstractJobEntity extends AbstractEntity{

    private String description;
    private String userId;

    public AbstractJobEntity() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    protected AbstractJobEntity(String id, String name, String description, String userId) {
        super(id, name);
        this.description = description;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", description='" + description + '\'';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
