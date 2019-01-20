package org.dragard.projectmanager.server.api.endpoint;

import org.dragard.projectmanager.server.entity.Response;
import org.dragard.projectmanager.server.entity.Task;

public interface TaskEndpoint extends EntityEndpoint<Task> {

    Response create(Task task, String token);

    Response update(String id, String name, String description, String token);
}
