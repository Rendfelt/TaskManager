package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.endpoint.Response;
import org.dragard.projectmanager.endpoint.TaskEndpointImplService;
import org.dragard.projectmanager.util.UtilClass;

public class TaskServiceImpl
    implements TaskService {

    private TaskEndpointImplService taskEndpoint;

    public TaskServiceImpl() {
    }

    public TaskServiceImpl(TaskEndpointImplService taskEndpoint) {
        this.taskEndpoint = taskEndpoint;
    }

    public TaskEndpointImplService getTaskEndpoint() {
        return taskEndpoint;
    }

    public void setTaskEndpoint(TaskEndpointImplService taskEndpoint) {
        this.taskEndpoint = taskEndpoint;
    }

    @Override
    public Response create(String name, String description, String projectId, String token) throws Exception {
        Response response = getTaskEndpoint().getTaskEndpointImplPort().createTask(name, description, projectId, token);
        UtilClass.checkResponse(response);

        return response;
    }

    @Override
    public Response update(String id, String name, String description, String token) throws Exception {
        Response response = getTaskEndpoint().getTaskEndpointImplPort().updateTask(id, name, description, token);
        UtilClass.checkResponse(response);

        return response;
    }

    @Override
    public Response delete(String id, String token) throws Exception {
        Response response = getTaskEndpoint().getTaskEndpointImplPort().deleteTask(id, token);
        UtilClass.checkResponse(response);

        return response;
    }

    @Override
    public Response getView(String token) throws Exception {
        Response response = getTaskEndpoint().getTaskEndpointImplPort().getViewTask(token);
        UtilClass.checkResponse(response);

        return response;
    }

/*    @Override
    public Response persist(List elements, String token) throws Exception {
        Response response = getTaskEndpoint().getTaskEndpointImplPort().persistTask(elements, token);
        UtilClass.checkResponse(response);

        return response;
    }*/
}
