package org.dragard.projectmanager.server.api.endpoint;

import org.dragard.projectmanager.server.entity.Project;
import org.dragard.projectmanager.server.entity.Response;

public interface ProjectEndpoint extends EntityEndpoint<Project> {

    Response create(String name, String description, String token);

    Response update(String id, String name, String description, String token);
}
