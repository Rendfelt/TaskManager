package org.dragard.projectmanager.endpoint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.endpoint.service.ProjectEndpointService;
import org.dragard.projectmanager.endpoint.service.ProjectEndpointServiceImpl;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Response;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Collection;

@NoArgsConstructor
@WebService
public class ProjectEndpointImpl {
    
    @Getter
    @Setter
    private ProjectEndpointServiceImpl projectEndpoint;

    public ProjectEndpointImpl(ProjectEndpointServiceImpl projectEndpoint) {
        this.projectEndpoint = projectEndpoint;
    }

    @WebMethod(operationName = "createProject")
    public @WebResult(name = "response") Response createProject(
            @WebParam(name = "name")String name,
            @WebParam(name = "description")String description,
            @WebParam(name = "token")String token
    ) {
        return projectEndpoint.create(name, description, token);
    }

    @WebMethod(operationName = "updateProject")
    public Response updateProject(
            @WebParam(name = "id") String id,
            @WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "token") String token) {
        return projectEndpoint.update(id, name, description, token);
    }

    @WebMethod(operationName = "deleteProject")
    public Response deleteProject(
            @WebParam(name = "id") String id,
            @WebParam(name = "token") String token) {
        return projectEndpoint.delete(id, token);
    }

    @WebMethod(operationName = "getViewProject")
    public Response getViewProject(
            @WebParam(name = "token") String token) {
        return projectEndpoint.getView(token);
    }

    @WebMethod(operationName = "persistProject")
    public Response persistProject(
            @WebParam(name = "elements") Collection<Project> elements,
            @WebParam(name = "token") String token) {
        return projectEndpoint.persist(elements, token);
    }
}
