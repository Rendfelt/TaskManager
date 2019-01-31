package org.dragard.projectmanager.endpoint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.api.endpoint.service.TaskEndpointService;
import org.dragard.projectmanager.endpoint.service.TaskEndpointServiceImpl;
import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@NoArgsConstructor
@WebService
public class TaskEndpointImpl{
    
    private Bootstrap bootstrap;

    @Getter
    @Setter
    private TaskEndpointServiceImpl taskEndpoint;

    public TaskEndpointImpl(TaskEndpointServiceImpl taskEndpoint) {
        this.taskEndpoint = taskEndpoint;
    }

    @WebMethod(operationName = "createTask")
    public Response createTask(
            @WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "projectId") String projectId,
            @WebParam(name = "token") String token
    ) {
        return taskEndpoint.create(name, description, projectId, token);
    }

    @WebMethod(operationName = "updateTask")
    public Response updateTask(
            @WebParam(name = "id") String id,
            @WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "token") String token
    ) {
        return taskEndpoint.update(id, name, description, token);
    }

    @WebMethod(operationName = "deleteTask")
    public Response deleteTask(
            @WebParam(name = "id") String id,
            @WebParam(name = "token") String token
    ) {
        return taskEndpoint.delete(id, token);
    }

    @WebMethod(operationName = "getViewTask")
    public Response getViewTask(
            @WebParam(name = "token") String token
    ) {
        return taskEndpoint.getView(token);
    }

    @WebMethod(operationName = "persistTask")
    public Response persistTask(
            @WebParam(name = "elements") Collection<Task> elements,
            @WebParam(name = "token") String token
    ) {
        return taskEndpoint.persist(elements, token);
    }

}
