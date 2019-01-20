package org.dragard.projectmanager.server.api.service;

import org.dragard.projectmanager.server.entity.Project;
import org.dragard.projectmanager.server.exception.NoElementWithIdException;
import org.dragard.projectmanager.server.exception.NoNameException;

public interface ProjectService extends EntityService<Project> {

    Project create(String name, String description, String userId) throws NoNameException;

    Project update(String id, String name, String description) throws NoNameException, NoElementWithIdException;
}
