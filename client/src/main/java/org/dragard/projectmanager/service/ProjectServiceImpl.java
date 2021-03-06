package org.dragard.projectmanager.service;


import lombok.AccessLevel;
import lombok.Getter;
import org.dragard.projectmanager.api.annotation.ResponceHandle;
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.endpoint.ProjectEndpointImplService;
import org.dragard.projectmanager.endpoint.Response;
import org.dragard.projectmanager.endpoint.TaskEndpointImplService;
import org.dragard.projectmanager.util.UtilClass;

import javax.enterprise.context.ApplicationScoped;

@ResponceHandle
@ApplicationScoped
public class ProjectServiceImpl
    implements ProjectService {

    @Getter(value = AccessLevel.PROTECTED)
    private final ProjectEndpointImplService projectEndpoint;

    protected ProjectServiceImpl() {
        this.projectEndpoint = new ProjectEndpointImplService();
    }

    @Override
    public Response create(String name, String description, String token) throws Exception {
        return getProjectEndpoint().getProjectEndpointImplPort().createProject(name, description, token);
    }

    @Override
    public Response update(String id, String name, String description, String token) throws Exception {
        return getProjectEndpoint().getProjectEndpointImplPort().updateProject(id, name, description, token);
    }

    @Override
    public Response delete(String id, String token) {
        return getProjectEndpoint().getProjectEndpointImplPort().deleteProject(id, token);
    }

    @Override
    public Response getView(String token) {
        return getProjectEndpoint().getProjectEndpointImplPort().getViewProject(token);
    }

/*    @Override
    public Response persist(List<Project> elements, String token) throws Exception {
        Response response = getProjectEndpoint().getProjectEndpointImplPort().persistProject(elements, token);
        UtilClass.checkResponse(response);

        return response;
    }*/
}
