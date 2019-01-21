package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;

public interface ProjectService extends EntityService<Project> {

    Project create(String name, String description, String userId) throws NoNameException;

    Project update(String id, String name, String description) throws NoNameException, NoElementWithIdException;
}
