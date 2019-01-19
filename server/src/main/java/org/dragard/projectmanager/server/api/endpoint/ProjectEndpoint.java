package org.dragard.projectmanager.server.api.endpoint;

import org.dragard.projectmanager.server.entity.Project;

public interface ProjectEndpoint extends EntityEndpoint<Project> {
    String create(String name, String description);

    String update(String id, String name, String description);
}
