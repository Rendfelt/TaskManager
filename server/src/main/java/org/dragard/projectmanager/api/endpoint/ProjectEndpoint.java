package org.dragard.projectmanager.api.endpoint;

import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Response;

public interface ProjectEndpoint extends EntityEndpoint<Project> {

    Response create(String name, String description, String token);

    Response update(String id, String name, String description, String token);
}
