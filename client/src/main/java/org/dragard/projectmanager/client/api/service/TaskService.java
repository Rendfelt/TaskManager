package org.dragard.projectmanager.client.api.service;

import org.dragard.projectmanager.client.endpoint.Response;

public interface TaskService extends EntityService {

    Response create(String name, String description, String projectId, String token) throws Exception;

    Response update(String id, String name, String description, String token) throws Exception;
}
