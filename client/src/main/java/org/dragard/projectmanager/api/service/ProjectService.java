package org.dragard.projectmanager.api.service;


import org.dragard.projectmanager.endpoint.Project;
import org.dragard.projectmanager.endpoint.Response;

public interface ProjectService extends EntityService<Project> {

    Response create(String name, String description, String token) throws Exception;

    Response update(String id, String name, String description, String token) throws Exception;

}
