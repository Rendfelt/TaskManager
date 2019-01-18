package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;

public interface ProjectService extends EntityService<Project> {

    void create(String name, String description, String userId) throws NoNameException;

    void update(String id, String name, String description, String userId) throws NoNameException, NoElementWithIdException;
}
