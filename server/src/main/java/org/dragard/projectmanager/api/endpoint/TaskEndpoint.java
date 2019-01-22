package org.dragard.projectmanager.api.endpoint;

import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.Task;

public interface TaskEndpoint extends EntityEndpoint<Task> {

    Response create(String name, String description, String projectId, String token);

    Response update(String id, String name, String description, String token);
}
