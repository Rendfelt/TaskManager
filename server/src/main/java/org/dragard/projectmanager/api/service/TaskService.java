package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;

public interface TaskService extends EntityService<Task> {

    void create(String name, String description, String projectId, String userId) throws NoNameException, NoElementWithIdException;

    void update(String id, String name, String description, String userId) throws NoNameException, NoElementWithIdException;

    void deleteTasksByProjectId(String projectId);
}