package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.Task;

public interface TaskService extends EntityService<Task> {

    Task create(String name, String description, String projectId, String userId) throws Exception;

    Task update(String id, String name, String description, String userId) throws Exception;

    void deleteTasksByProjectId(String projectId) throws Exception;
}
