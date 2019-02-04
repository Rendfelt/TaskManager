package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.endpoint.Response;

public interface TaskService extends EntityService {

    Response create(String name, String description, String projectId, String token);

    Response update(String id, String name, String description, String token);
}
