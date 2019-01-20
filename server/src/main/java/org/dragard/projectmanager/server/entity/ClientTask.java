package org.dragard.projectmanager.server.entity;

import java.io.Serializable;

public class ClientTask
    implements Serializable {

    private String id;
    private String name;
    private String description;
    private String userId;
    private String projectId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ClientTask() {
    }

    public ClientTask(String id, String name, String description, String userId, String projectId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.projectId = projectId;
    }
}
