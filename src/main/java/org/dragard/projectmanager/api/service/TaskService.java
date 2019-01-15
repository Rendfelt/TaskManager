package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;

public interface TaskService extends Service<Task> {

    void create(String name, String description, String projectId) throws NoNameException, NoElementWithIdException;

    void update(String id, String name, String description) throws NoNameException, NoElementWithIdException;

    void deleteTasksByProjectId(String projectId);
}
