package org.dragard.projectmanager.client.api.service;


import org.dragard.projectmanager.client.endpoint.Project;
import org.dragard.projectmanager.client.endpoint.Response;

public interface ProjectService extends EntityService<Project> {

    Response create(String name, String description, String token) throws Exception;

    Response update(String id, String name, String description, String token) throws Exception;

}
