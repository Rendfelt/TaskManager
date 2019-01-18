package org.dragard.projectmanager.entity;

public class Task extends AbstractJobEntity {

    public Task() {
    }

    private String projectId;

    public Task(String id, String name, String description, String projectId) {
        super(id, name, description);
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
