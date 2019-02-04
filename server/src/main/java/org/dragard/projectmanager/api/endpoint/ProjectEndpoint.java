package org.dragard.projectmanager.api.endpoint;

import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Response;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface ProjectEndpoint extends Endpoint{

    @WebMethod(operationName = "createProject")
    @WebResult(name = "response")
    Response createProject(
            @WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "token") String token
    );

    @WebMethod(operationName = "updateProject")
    Response updateProject(
            @WebParam(name = "id") String id,
            @WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "token") String token);

    @WebMethod(operationName = "deleteProject")
    Response deleteProject(
            @WebParam(name = "id") String id,
            @WebParam(name = "token") String token);

    @WebMethod(operationName = "getViewProject")
    Response getViewProject(
            @WebParam(name = "token") String token);

    @WebMethod(operationName = "persistProject")
    Response persistProject(
            @WebParam(name = "elements") Collection<Project> elements,
            @WebParam(name = "token") String token);
}
