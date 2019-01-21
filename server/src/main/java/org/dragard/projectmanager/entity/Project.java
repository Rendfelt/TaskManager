package org.dragard.projectmanager.entity;

public class Project extends AbstractJobEntity {

    public Project(String id, String name, String description, String userId) {
        super(id, name, description, userId);
    }

    public Project() {
    }
}
