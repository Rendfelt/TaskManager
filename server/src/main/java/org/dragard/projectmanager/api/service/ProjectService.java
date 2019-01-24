package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.Project;

public interface ProjectService extends JobEntityService<Project> {

    Project create(String name, String description, String userId) throws Exception;

    Project update(String id, String name, String description) throws Exception;
}
