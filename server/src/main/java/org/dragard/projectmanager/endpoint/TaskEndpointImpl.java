package org.dragard.projectmanager.endpoint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.api.endpoint.TaskEndpoint;
import org.dragard.projectmanager.api.endpoint.service.TaskEndpointService;
import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@WebService
@NoArgsConstructor
public class TaskEndpointImpl
    implements TaskEndpoint {

    private TaskEndpointService taskEndpoint;

    public TaskEndpointImpl(TaskEndpointService taskEndpoint) {
        this.taskEndpoint = taskEndpoint;
    }

    @Override
    @WebMethod(operationName = "createTask")
    public Response createTask(
            @WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "projectId") String projectId,
            @WebParam(name = "token") String token
    ) {
        return taskEndpoint.create(name, description, projectId, token);
    }

    @Override
    @WebMethod(operationName = "updateTask")
    public Response updateTask(
            @WebParam(name = "id") String id,
            @WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "token") String token
    ) {
        return taskEndpoint.update(id, name, description, token);
    }

    @Override
    @WebMethod(operationName = "deleteTask")
    public Response deleteTask(
            @WebParam(name = "id") String id,
            @WebParam(name = "token") String token
    ) {
        return taskEndpoint.delete(id, token);
    }

    @Override
    @WebMethod(operationName = "getViewTask")
    public Response getViewTask(
            @WebParam(name = "token") String token
    ) {
        return taskEndpoint.getView(token);
    }

    @Override
    @WebMethod(operationName = "persistTask")
    public Response persistTask(
            @WebParam(name = "elements") Collection<Task> elements,
            @WebParam(name = "token") String token
    ) {
        return taskEndpoint.persist(elements, token);
    }

}
