package org.dragard.projectmanager.service;


import lombok.AccessLevel;
import lombok.Getter;
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.endpoint.ProjectEndpointImplService;
import org.dragard.projectmanager.endpoint.Response;
import org.springframework.stereotype.Component;

@Component
public class ProjectServiceImpl
    implements ProjectService {

    @Getter(value = AccessLevel.PROTECTED)
    private final ProjectEndpointImplService projectEndpoint;

    protected ProjectServiceImpl() {
        this.projectEndpoint = new ProjectEndpointImplService();
    }

    @Override
    public Response create(String name, String description, String token) {
        return getProjectEndpoint().getProjectEndpointImplPort().createProject(name, description, token);
    }

    @Override
    public Response update(String id, String name, String description, String token) {
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

}
