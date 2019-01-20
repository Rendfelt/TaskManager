package org.dragard.projectmanager.server.api.service;

import org.dragard.projectmanager.server.entity.Task;
import org.dragard.projectmanager.server.exception.NoElementWithIdException;
import org.dragard.projectmanager.server.exception.NoNameException;

public interface TaskService extends EntityService<Task> {

    Task create(String name, String description, String projectId, String userId) throws NoNameException, NoElementWithIdException;

    Task update(String id, String name, String description, String userId) throws NoNameException, NoElementWithIdException;

    void deleteTasksByProjectId(String projectId);
}
