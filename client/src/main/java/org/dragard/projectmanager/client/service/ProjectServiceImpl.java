package org.dragard.projectmanager.client.service;


import org.dragard.projectmanager.client.api.service.ProjectService;
import org.dragard.projectmanager.client.endpoint.Project;
import org.dragard.projectmanager.client.endpoint.ProjectEndpointImplService;
import org.dragard.projectmanager.client.endpoint.Response;

import java.util.List;

import org.dragard.projectmanager.client.util.UtilClass;

public class ProjectServiceImpl
    implements ProjectService {

    public ProjectEndpointImplService getProjectEndpoint() {
        return projectEndpoint;
    }

    private final ProjectEndpointImplService projectEndpoint;

    public ProjectServiceImpl(ProjectEndpointImplService projectEndpoint) {
        this.projectEndpoint = projectEndpoint;
    }

    @Override
    public Response create(String name, String description, String token) throws Exception {
        Response response = getProjectEndpoint().getProjectEndpointImplPort().create(name, description, token);
        UtilClass.checkResponse(response);

        return response;
    }

    @Override
    public Response update(String id, String name, String description, String token) throws Exception {
        Response response = getProjectEndpoint().getProjectEndpointImplPort().update(id, name, description, token);
        UtilClass.checkResponse(response);

        return response;
    }

    @Override
    public Response delete(String id, String token) throws Exception {
        Response response = getProjectEndpoint().getProjectEndpointImplPort().delete(id, token);
        UtilClass.checkResponse(response);

        return response;
    }

    @Override
    public Response getView(String token) throws Exception {
        Response response = getProjectEndpoint().getProjectEndpointImplPort().getView(token);
        UtilClass.checkResponse(response);

        return response;
    }

    @Override
    public Response persist(List<Project> elements, String token) throws Exception {
        Response response = getProjectEndpoint().getProjectEndpointImplPort().persist(elements, token);
        UtilClass.checkResponse(response);

        return response;
    }
}
