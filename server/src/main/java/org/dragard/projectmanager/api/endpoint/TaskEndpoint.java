package org.dragard.projectmanager.api.endpoint;

import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.Task;

import javax.jws.WebParam;

public interface TaskEndpoint extends EntityEndpoint<Task> {

    Response create(@WebParam(name = "name") String name, @WebParam(name = "description") String description, @WebParam(name = "projectId") String projectId, @WebParam(name = "token") String token);

    Response update(@WebParam(name = "id") String id, @WebParam(name = "name") String name, @WebParam(name = "description") String description, @WebParam(name = "token") String token);
}
