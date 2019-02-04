package org.dragard.projectmanager.api.endpoint;

import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface TaskEndpoint {

    @WebMethod(operationName = "createTask")
    Response createTask(
            @WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "projectId") String projectId,
            @WebParam(name = "token") String token
    );

    @WebMethod(operationName = "updateTask")
    Response updateTask(
            @WebParam(name = "id") String id,
            @WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "token") String token
    );

    @WebMethod(operationName = "deleteTask")
    Response deleteTask(
            @WebParam(name = "id") String id,
            @WebParam(name = "token") String token
    );

    @WebMethod(operationName = "getViewTask")
    Response getViewTask(
            @WebParam(name = "token") String token
    );

    @WebMethod(operationName = "persistTask")
    Response persistTask(
            @WebParam(name = "elements") Collection<Task> elements,
            @WebParam(name = "token") String token
    );
}
