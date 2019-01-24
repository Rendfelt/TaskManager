package org.dragard.projectmanager.service;


import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.endpoint.ProjectEndpointImplService;
import org.dragard.projectmanager.endpoint.Response;
import org.dragard.projectmanager.endpoint.TaskEndpointImplService;
import org.dragard.projectmanager.util.UtilClass;

public class ProjectServiceImpl
    implements ProjectService {

    public static ProjectService getInstance(ProjectEndpointImplService projectEndpointImplService){
        return (ProjectService) UtilClass.getServiceProxy(ProjectService.class, new ProjectServiceImpl(projectEndpointImplService));
    }

    public ProjectEndpointImplService getProjectEndpoint() {
        return projectEndpoint;
    }

    private final ProjectEndpointImplService projectEndpoint;

    private ProjectServiceImpl(ProjectEndpointImplService projectEndpoint) {
        this.projectEndpoint = projectEndpoint;
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
    public Response delete(String id, String token) throws Exception {
        return getProjectEndpoint().getProjectEndpointImplPort().deleteProject(id, token);
    }

    @Override
    public Response getView(String token) throws Exception {
        return getProjectEndpoint().getProjectEndpointImplPort().getViewProject(token);
    }

/*    @Override
    public Response persist(List<Project> elements, String token) throws Exception {
        Response response = getProjectEndpoint().getProjectEndpointImplPort().persistProject(elements, token);
        UtilClass.checkResponse(response);

        return response;
    }*/
}
