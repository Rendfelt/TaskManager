package org.dragard.projectmanager.server.entity;

public class Task extends AbstractJobEntity {

    public Task() {
    }

    private String projectId;

    public Task(String id, String name, String description, String projectId, String userId) {
        super(id, name, description, userId);
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", projectId='" + projectId + '\'';
    }
}
